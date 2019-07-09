package com.suyu.voting.app.dtos;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author NieZhiLiang
 * @Email nzlsgg@163.com
 * @GitHub https://github.com/niezhiliang
 * @Date 2019/07/04 11:38
 */
@Data
public class AddVoteDTO {

    /**
     * 主题
     */
    private String title;

    /**
     * 描述
     */
    private String descript;

    /**
     * 投票开启时间
     */
    private Date begin;

    /**
     * 投票截止时间
     */
    private Date end;

    /**
     * 投票类型 1.单选 2.多选
     */
    private Integer type;

    /**
     * 多选票数
     */
    private Integer count;

    /**
     * 候选人信息
     */
    private List<CandidateDTO> candidates;

}
