package com.suyu.voting.app.vos;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author NieZhiLiang
 * @Email nzlsgg@163.com
 * @GitHub https://github.com/niezhiliang
 * @Date 2019/07/04 15:02
 */
@Data
public class VoteListVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 投票编号
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 参与人数
     */
    private Integer count;

    /**
     * 发起人昵称
     */
    private String initiatorNickName;

    /**
     * 发起人头像
     */
    private String avatarImg;

    /**
     * 是否是自己发起的
     */
    private boolean isSelf;

    /**
     * 当前投票状态
     */
    private Integer status;

    /**
     * 投票简介
     */
    private String descript;

    /**
     * 开始时间
     */
    private Date begin;

    /**
     * 截止时间
     */
    private Date end;

}
