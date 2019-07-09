package com.suyu.voting.app.controller;


import com.suyu.voting.app.common.ResultUtils;
import com.suyu.voting.app.common.ResultVO;
import com.suyu.voting.app.dtos.AddVoteDTO;
import com.suyu.voting.app.service.IThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Suyu
 * @since 2019-07-04
 */
@RestController
@RequestMapping("/theme")
public class ThemeController {

    @Autowired
    private IThemeService themeService;

    /**
     * 发起投票
     * @param addVoteDTO
     * @return
     */
    @PostMapping(value = "add")
    public ResultVO add(@RequestBody AddVoteDTO addVoteDTO) {
        themeService.addVote(addVoteDTO);
        return ResultUtils.success();
    }


    /**
     * 获取所有的投票
     * @return
     */
    @GetMapping(value = "list")
    public ResultVO themeList() {
        return ResultUtils.success(themeService.getVoteList());
    }

    /**
     * 获取投票的实时结果
     * @param id
     * @return
     */
    @GetMapping(value = "result")
    public ResultVO result(@RequestParam(value = "id") Integer id) {

        return ResultUtils.success(themeService.getResult(id));

    }
}
