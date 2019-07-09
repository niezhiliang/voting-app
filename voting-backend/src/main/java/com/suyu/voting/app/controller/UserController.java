package com.suyu.voting.app.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.suyu.voting.app.dtos.LoginDTO;
import com.suyu.voting.app.service.IUserService;
import com.suyu.voting.app.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Suyu
 * @since 2019-07-04
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    //wx13b92d5b8923bcb9
    //08ae21513a6e3c3030950732748f7384

    @Autowired
    private IUserService userService;


    @RequestMapping(value = "openId")
    public String getOpenId(@RequestBody LoginDTO loginDTO) {
        StringBuffer url = new StringBuffer();
        /*********获取token************/
        url.append("https://api.weixin.qq.com/sns/jscode2session?")
                .append("appid=")
                .append(loginDTO.getAppid())
                .append("&secret=")
                .append(loginDTO.getSecret())
                .append("&js_code=")
                .append(loginDTO.getJs_code())
                .append("&grant_type=")
                .append(loginDTO.getGrant_type());
        log.info(url.toString());

        String result = HttpUtil.getResult(url.toString());

        JSONObject resultJson = JSON.parseObject(result,JSONObject.class);

        userService.addOrUpdateUser(resultJson.getString("openid"),loginDTO);

        return result;
    }


    @RequestMapping(value = "userInfo")
    public String getUserInfo() {

        return "";
    }

}
