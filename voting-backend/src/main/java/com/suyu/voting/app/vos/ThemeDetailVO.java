package com.suyu.voting.app.vos;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author NieZhiLiang
 * @Email nzlsgg@163.com
 * @GitHub https://github.com/niezhiliang
 * @Date 2019/07/05 15:42
 */
@Data
public class ThemeDetailVO {

    /**
     * id
     */
    private Integer id;

    /**
     * 投票主题
     */
    private String title;

    /**
     * 投票描述
     */
    private String descript;

    /**
     * 投票截止时间
     */
    private Date endAt;

    /**
     * 已参与人数
     */
    private Integer count;

    /**
     * 参选人获得票数详情
     */
    private List<MemberDataVO> memberDatas;

    /**
     * 当前投票状态
     */
    private Integer status;
}
