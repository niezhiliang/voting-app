package com.suyu.voting.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.suyu.voting.app.dtos.LoginDTO;
import com.suyu.voting.app.entity.User;
import com.suyu.voting.app.mapper.UserMapper;
import com.suyu.voting.app.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Suyu
 * @since 2019-07-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    @Override
    public boolean addOrUpdateUser(String openId, LoginDTO loginDTO) {

        User user = this.getOne(new QueryWrapper<User>().lambda()
                    .eq(User::getOpenId,openId));

        if (user == null) {
            user = new User();
            user.setCreateAt(new Date());
        }
        user.setAvatarImg(loginDTO.getAvatarUrl());
        user.setSex(loginDTO.getSex());
        user.setOpenId(openId);
        user.setNickName(loginDTO.getNickName());
        user.setLastLoginAt(new Date());

        return this.saveOrUpdate(user);
    }
}
