package com.suyu.voting.app.dtos;

import lombok.Data;

import java.util.List;

/**
 * @Author NieZhiLiang
 * @Email nzlsgg@163.com
 * @GitHub https://github.com/niezhiliang
 * @Date 2019/07/04 15:30
 */
@Data
public class VoteUpDTO {

    private Integer id;

    private List<Integer> memberIds;

}
