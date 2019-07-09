package com.suyu.voting.app.service;

import com.suyu.voting.app.dtos.AddVoteDTO;
import com.suyu.voting.app.entity.Theme;
import com.baomidou.mybatisplus.extension.service.IService;
import com.suyu.voting.app.vos.MemberDataVO;
import com.suyu.voting.app.vos.ThemeDetailVO;
import com.suyu.voting.app.vos.VoteListVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Suyu
 * @since 2019-07-04
 */
public interface IThemeService extends IService<Theme> {

    /**
     * 发起投票
     * @param addVoteDTO
     * @return
     */
    void addVote(AddVoteDTO addVoteDTO);

    /**
     * 查询属于我的投票项目
     * @return
     */
    List<VoteListVO> getVoteList();

    /**
     * 获取实时投票情况
     * @param themeId
     * @return
     */
    ThemeDetailVO getResult(Integer themeId);

    /**
     * 开启投票
     * @param id
     * @return
     */
    boolean openTheme(Integer id,boolean flag);

}
