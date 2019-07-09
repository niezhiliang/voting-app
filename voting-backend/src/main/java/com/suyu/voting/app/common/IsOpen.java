package com.suyu.voting.app.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author NieZhiLiang
 * @Email nzlsgg@163.com
 * @GitHub https://github.com/niezhiliang
 * @Date 2019/07/05 14:50
 */
@Getter
@AllArgsConstructor
public enum IsOpen {

    WAIT(1,"投票暂未开始"),

    OPEN(2,"投票已经打开"),

    SUCCESS(3,"投票已完成"),

    ;
    private Integer type;

    private String msg;

}
