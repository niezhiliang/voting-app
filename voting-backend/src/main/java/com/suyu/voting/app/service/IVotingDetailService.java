package com.suyu.voting.app.service;

import com.suyu.voting.app.dtos.VoteUpDTO;
import com.suyu.voting.app.entity.VotingDetail;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 投票详情 服务类
 * </p>
 *
 * @author Suyu
 * @since 2019-07-04
 */
public interface IVotingDetailService extends IService<VotingDetail> {
    /**
     * 投票
     */
    void voteUp(VoteUpDTO voteUpDTO);

}
