package com.suyu.voting.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.suyu.voting.app.common.IsOpen;
import com.suyu.voting.app.dtos.AddVoteDTO;
import com.suyu.voting.app.entity.Members;
import com.suyu.voting.app.entity.Theme;
import com.suyu.voting.app.entity.User;
import com.suyu.voting.app.entity.VotingDetail;
import com.suyu.voting.app.mapper.MembersMapper;
import com.suyu.voting.app.mapper.ThemeMapper;
import com.suyu.voting.app.quartz.JobInvoke;
import com.suyu.voting.app.quartz.QuartzService;
import com.suyu.voting.app.service.IThemeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suyu.voting.app.service.IUserService;
import com.suyu.voting.app.service.IVotingDetailService;
import com.suyu.voting.app.utils.CanonUtils;
import com.suyu.voting.app.utils.JWTUtils;
import com.suyu.voting.app.vos.MemberDataVO;
import com.suyu.voting.app.vos.ThemeDetailVO;
import com.suyu.voting.app.vos.VoteListVO;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Suyu
 * @since 2019-07-04
 */
@Service
@Slf4j
public class ThemeServiceImpl extends ServiceImpl<ThemeMapper, Theme> implements IThemeService {

    @Autowired
    private ThemeMapper themeMapper;

    @Autowired
    private MembersMapper membersMapper;

    @Autowired
    private IVotingDetailService votingDetailService;

    @Autowired
    private IUserService userService;

    @Autowired
    private QuartzService quartzService;

    @Override
    @Transactional
    public void addVote(AddVoteDTO addVoteDTO) {
        Theme theme = new Theme();
        theme.setUserid(JWTUtils.getUserId())
                .setTitle(addVoteDTO.getTitle())
                .setDescription(addVoteDTO.getDescript())
                .setBeginAt(addVoteDTO.getBegin())
                .setEndAt(addVoteDTO.getEnd())
                .setType(addVoteDTO.getType())
                .setCreateAt(new Date())
                .setManyCount(addVoteDTO.getCount());
        themeMapper.insert(theme);
        //添加投票候选人
        addVoteDTO.getCandidates()
                .forEach(candidater -> {
                    Members members = new Members();
                    members.setCreateAt(new Date())
                            .setName( candidater.getName())
                            .setThemeId(theme.getId());

                    membersMapper.insert(members);
                });
        try {
            //添加启动投票的定时任务
            quartzService.addJob(JobInvoke.class,theme.getId(),true,addVoteDTO.getBegin());

            //关闭投票的定时任务
            quartzService.addJob(JobInvoke.class,theme.getId(),false,addVoteDTO.getEnd());
        } catch (SchedulerException e) {
            e.printStackTrace();
            log.error("投票定时任务设置失败");
        }
    }

    @Override
    public List<VoteListVO> getVoteList() {

        List<VoteListVO> vos = new ArrayList<>();

        List<Theme> themes = this.getBaseMapper().selectList(
                new QueryWrapper<Theme>()
                .lambda()
                .orderByDesc());

        themes.forEach(theme -> {
            //获取投票的总人数
            int count =  votingDetailService.count(new QueryWrapper<VotingDetail>()
                    .lambda().eq(VotingDetail::getMemberId,theme.getId()));
            //查询发起人信息
            User user = userService.getById(theme.getUserid());
            //拼装返回的单个对象
            VoteListVO voteListVO = new VoteListVO();
            voteListVO.setId(theme.getId());
            voteListVO.setAvatarImg(user.getAvatarImg());
            voteListVO.setInitiatorNickName(user.getNickName());
            voteListVO.setTitle(theme.getTitle());
            voteListVO.setCount(count);
            voteListVO.setSelf(user.getId().equals(theme.getUserid()));
            voteListVO.setStatus(theme.getStatus());
            voteListVO.setDescript(theme.getDescription());
            voteListVO.setBegin(theme.getBeginAt());
            voteListVO.setEnd(theme.getEndAt());

            vos.add(voteListVO);
        });

        return vos;
    }

    @Override
    public ThemeDetailVO getResult(Integer themeId) {

        Theme theme = themeMapper.selectById(themeId);

        List<Members> members = membersMapper.selectList(new QueryWrapper<Members>()
                .lambda().eq(Members::getThemeId,themeId));

        //获取所有的候选人id
        List<Integer> memberIds = members.stream().map(Members::getId).collect(Collectors.toList());

        //总投票数
        Integer allCount = votingDetailService.count(new QueryWrapper<VotingDetail>()
                .lambda().in(VotingDetail::getMemberId,memberIds));

        List<MemberDataVO> memberDataVOS = new ArrayList<>();

        members.forEach(member -> {
            MemberDataVO memberDataVO = new MemberDataVO();
            //候选人获取的投票数
            Integer count = votingDetailService.count(new QueryWrapper<VotingDetail>()
                        .lambda().eq(VotingDetail::getMemberId,member.getId()));

            memberDataVO.setName(member.getName());
            memberDataVO.setCount(count);
            //计算所占百分比
            String percentage = CanonUtils.adivideBPercent(new BigDecimal(count),new BigDecimal(allCount));
            memberDataVO.setPercentage(percentage);
            memberDataVO.setMemberId(member.getId());

            memberDataVOS.add(memberDataVO);

        });

        ThemeDetailVO themeDetailVO = new ThemeDetailVO();
        themeDetailVO.setCount(allCount);
        themeDetailVO.setDescript(theme.getDescription());
        themeDetailVO.setEndAt(theme.getEndAt());
        themeDetailVO.setId(theme.getId());
        themeDetailVO.setTitle(theme.getTitle());
        themeDetailVO.setStatus(theme.getStatus());
        //如果投票完成,则排序输出
        if (theme.getStatus().equals(IsOpen.SUCCESS.getType())) {
           List<MemberDataVO> memberDataVOList  =
                   memberDataVOS.stream().sorted((a, b) -> a.getCount() - b.getCount()).collect(Collectors.toList());
           themeDetailVO.setMemberDatas(memberDataVOList);
        } else {
            themeDetailVO.setMemberDatas(memberDataVOS);
        }

        return themeDetailVO;
    }

    @Override
    public boolean openTheme(Integer id,boolean flag) {
        Theme theme = this.getById(id);
        if (theme == null) {
            log.error("该投票不存在，开启失败,id:{},",id);
            return false;
        }
        //只有投票状态中才可以设置为投票已完成
        if ((!flag) && theme.getStatus().equals(IsOpen.OPEN.getType())) {
            theme.setStatus(IsOpen.SUCCESS.getType());
        }
        //只有投票未开始状态才能将投票设置为开启
        if (flag && theme.getStatus().equals(IsOpen.WAIT.getType())) {
            theme.setStatus(IsOpen.OPEN.getType());

        }
        return this.saveOrUpdate(theme);
    }
}
