package com.suyu.voting.app.controller;


import com.suyu.voting.app.common.ResultUtils;
import com.suyu.voting.app.common.ResultVO;
import com.suyu.voting.app.service.IMembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 投票候选人表 前端控制器
 * </p>
 *
 * @author Suyu
 * @since 2019-07-04
 */
@RestController
@RequestMapping("/members")
public class MembersController {

    @Autowired
    private IMembersService membersService;

    /**
     * 获取候选人的支持者
     * @param id
     * @return
     */
    @RequestMapping(value = "fans")
    public ResultVO getSuppers(@RequestParam(value = "id") Integer id) {

        return ResultUtils.success(membersService.getFans(id));
    }

}
