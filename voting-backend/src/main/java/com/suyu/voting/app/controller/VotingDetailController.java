package com.suyu.voting.app.controller;


import com.suyu.voting.app.common.ResultUtils;
import com.suyu.voting.app.common.ResultVO;
import com.suyu.voting.app.dtos.VoteUpDTO;
import com.suyu.voting.app.service.IVotingDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 投票详情 前端控制器
 * </p>
 *
 * @author Suyu
 * @since 2019-07-04
 */
@RestController
@RequestMapping("/vote")
public class VotingDetailController {

    @Autowired
    private IVotingDetailService votingDetailService;


    /**
     * 投票
     * @return
     */
    @PostMapping(value = "up")
    public ResultVO up(@RequestBody VoteUpDTO voteUpDTO) {
        votingDetailService.voteUp(voteUpDTO);
        return ResultUtils.success("投票成功");
    }

}
