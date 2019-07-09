package com.suyu.voting.app.service;

import com.suyu.voting.app.dtos.LoginDTO;
import com.suyu.voting.app.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Suyu
 * @since 2019-07-04
 */
public interface IUserService extends IService<User> {

    /**
     * 添加或修改用户信息
     * @return
     */
    boolean  addOrUpdateUser(String openId, LoginDTO loginDTO);

}
