package com.suyu.voting.app.vos;

import lombok.Data;

/**
 * @Author NieZhiLiang
 * @Email nzlsgg@163.com
 * @GitHub https://github.com/niezhiliang
 * @Date 2019/07/05 15:32
 */
@Data
public class MemberDataVO {

    /**
     * 候选人编号
     */
    private Integer memberId;

    /**
     * 候选人名称
     */
    private String name;

    /**
     * 所得到票数
     */
    private Integer count;

    /**
     * 所占百分比
     */
    private String percentage;
}
