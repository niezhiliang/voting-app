package com.suyu.voting.app.vos;

import lombok.Data;

import java.util.Date;

/**
 * @Author NieZhiLiang
 * @Email nzlsgg@163.com
 * @GitHub https://github.com/niezhiliang
 * @Date 2019/07/05 16:33
 */
@Data
public class FansVO {

    /**
     * 投票人昵称
     */
    private String nickName;

    /**
     * 投票人头像
     */
    private String avatarImg;

    /**
     * 投票时间
     */
    private Date createAt;
}
