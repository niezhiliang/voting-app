package com.suyu.voting.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.suyu.voting.app.common.IsOpen;
import com.suyu.voting.app.common.ResultCode;
import com.suyu.voting.app.common.ThemeType;
import com.suyu.voting.app.dtos.VoteUpDTO;
import com.suyu.voting.app.entity.Theme;
import com.suyu.voting.app.entity.VotingDetail;
import com.suyu.voting.app.exception.VoteException;
import com.suyu.voting.app.mapper.VotingDetailMapper;
import com.suyu.voting.app.service.IThemeService;
import com.suyu.voting.app.service.IVotingDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suyu.voting.app.utils.JWTUtils;
import com.suyu.voting.app.utils.LockUtils;
import com.suyu.voting.app.utils.UUIDUitls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
 * <p>
 * 投票详情 服务实现类
 * </p>
 *
 * @author Suyu
 * @since 2019-07-04
 */
@Service
public class VotingDetailServiceImpl extends ServiceImpl<VotingDetailMapper, VotingDetail> implements IVotingDetailService {

    @Autowired
    private IThemeService themeService;

    @Autowired
    private LockUtils lockUtils;

    private static final String LOCK_KEY = "LOCK_KEY_VOTE_UP";

    @Override
    @Transactional
    public void voteUp(VoteUpDTO voteUpDTO) {

        Theme theme = themeService.getById(voteUpDTO.getId());

        //投票入口还未开启
        if (!IsOpen.OPEN.getType().equals(theme.getStatus())) {
            throw new VoteException(ResultCode.VOTE_WATING);
        }
        //TODO 判断该投票人是否已经投过票

        //如果是单选的并且还传递了两个候选人id 直接抛异常
        if (theme.getType().equals(ThemeType.ALONE.getType()) && voteUpDTO.getMemberIds().size() > 1) {
            throw new VoteException(ResultCode.VOTE_TYPE_ERROR);
        }
        //如果是多选，投票的人数必须等于设置好的多选数量
        if (theme.getType().equals(ThemeType.MANY.getType())) {
            if (theme.getManyCount().intValue() != voteUpDTO.getMemberIds().size()) {
                throw new VoteException(ResultCode.VOTE_COUNT_ERROR);
            }
        }
        final String lockValue = UUIDUitls.uuidNoSplit();
        //默认尝试获取三次锁
        int num = 3;
        do {
            //获取到了锁
            if (lockUtils.lock(LOCK_KEY,lockValue)) {

                Integer userId = JWTUtils.getUserId();
                voteUpDTO.getMemberIds().forEach(memberId -> {
                    VotingDetail votingDetail = this.getOne(new QueryWrapper<VotingDetail>()
                            .lambda().eq(VotingDetail::getUserId,userId)
                            .and(wrapper-> wrapper.eq(VotingDetail::getMemberId,memberId)));
                    //如果已经有记录说明已经投过票啦
                    if (votingDetail != null) {
                        throw new VoteException(ResultCode.REVOTEUP);
                    }
                    votingDetail = new VotingDetail();
                    votingDetail.setCreateAt(new Date())
                            .setMemberId(memberId)
                            .setUserId(userId);

                    this.save(votingDetail);
                });
                //释放锁
                if (!lockUtils.unlock(LOCK_KEY,lockValue)) {
                    log.error("释放分布式锁失败");
                }
                break;
            }

            num--;
            //休眠1秒后再重新获取锁
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (num > 0);

    }
}
