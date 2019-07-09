package com.suyu.voting.app.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author NieZhiLiang
 * @Email nzlsgg@163.com
 * @GitHub https://github.com/niezhiliang
 * @Date 2019/07/04 16:06
 */
@Getter
@AllArgsConstructor
public enum ThemeType {

    ALONE(1,"单选"),

    MANY(2,"多选")

    ;
    private Integer type;

    private String msg;
}
