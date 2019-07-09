package com.suyu.voting.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author Suyu
 * @since 2019-07-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户昵称
     */
    @TableField("nickName")
    private String nickName;

    /**
     * 性别：1.男 2.女
     */
    private Integer sex;

    /**
     * 微信openId
     */
    @TableField("openId")
    private String openId;

    /**
     * 用户头像
     */
    @TableField("avatarImg")
    private String avatarImg;

    /**
     * 账号状态 1.正常 2.冻结
     */
    @TableField("accountStatus")
    private Integer accountStatus;

    /**
     * 首次登录时间
     */
    @TableField("createAt")
    private Date createAt;

    /**
     * 最近一次登录时间
     */
    @TableField("lastLoginAt")
    private Date lastLoginAt;


}
