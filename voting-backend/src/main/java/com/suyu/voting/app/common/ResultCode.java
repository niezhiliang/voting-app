package com.suyu.voting.app.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author NieZhiLiang
 * @Email nzlsgg@163.com
 * @GitHub https://github.com/niezhiliang
 * @Date 2019/07/04 11:19
 */
@AllArgsConstructor
@Getter
public enum ResultCode {

    /**
     * 失败状态
     */
    ERROR(0,"请求失败"),

    /**
     * 成功状态
     */
    SUCCESS(1,"请求成功"),

    /**
     * 投票类型错误
     */
    VOTE_TYPE_ERROR(2,"单选请勿多选"),

    REVOTEUP(3,"请勿重复投票"),

    VOTE_WATING(4,"投票还未开始"),

    VOTE_COUNT_ERROR(5,"请投对应选项数量的人数"),



    ;
    private Integer code;

    private String msg;
}
