package com.suyu.voting.app.dtos;

import lombok.Data;

/**
 * @Author NieZhiLiang
 * @Email nzlsgg@163.com
 * @GitHub https://github.com/niezhiliang
 * @Date 2019/07/05 19:38
 */
@Data
public class LoginDTO {

    private String  appid;

    private String secret;

    private String js_code;

    private String grant_type;

    private String nickName;

    private Integer sex;

    private String avatarUrl;
}
