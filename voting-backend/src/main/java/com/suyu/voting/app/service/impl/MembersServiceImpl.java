package com.suyu.voting.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.suyu.voting.app.entity.Members;
import com.suyu.voting.app.entity.User;
import com.suyu.voting.app.entity.VotingDetail;
import com.suyu.voting.app.mapper.MembersMapper;
import com.suyu.voting.app.mapper.UserMapper;
import com.suyu.voting.app.mapper.VotingDetailMapper;
import com.suyu.voting.app.service.IMembersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suyu.voting.app.vos.FansVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 投票候选人表 服务实现类
 * </p>
 *
 * @author Suyu
 * @since 2019-07-04
 */
@Service
public class MembersServiceImpl extends ServiceImpl<MembersMapper, Members> implements IMembersService {

    @Autowired
    private VotingDetailMapper votingDetailMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<FansVO> getFans(Integer id) {
        //获取的投票记录
        List<VotingDetail> votingDetails =
                votingDetailMapper.selectList(new QueryWrapper<VotingDetail>().lambda().eq(VotingDetail::getMemberId,id));

        List<FansVO> fansVOS = new ArrayList<>();

        votingDetails.forEach(votingDetail -> {

            User user = userMapper.selectById(votingDetail.getUserId());

            FansVO fansVO = new FansVO();
            fansVO.setAvatarImg(user.getAvatarImg());
            fansVO.setCreateAt(votingDetail.getCreateAt());
            fansVO.setNickName(user.getNickName());

            fansVOS.add(fansVO);

        });

        return fansVOS;
    }
}
