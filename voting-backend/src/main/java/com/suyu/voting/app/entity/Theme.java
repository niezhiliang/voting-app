package com.suyu.voting.app.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Suyu
 * @since 2019-07-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Theme implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 所属人
     */
    private Integer userid;

    /**
     * 投票主题
     */
    private String title;

    /**
     * 投票描述
     */
    private String description;

    /**
     * 投票开始时间
     */
    @TableField("beginAt")
    private Date beginAt;

    /**
     * 投票结束时间
     */
    @TableField("endAt")
    private Date endAt;

    /**
     * 投票类型：1.单选 2.多选
     */
    private Integer type;

    /**
     * 多选投的票数
     */
    @TableField("manyCount")
    private Integer manyCount;

    /**
     * 当前状态：1待开启 2.投票中 3.投票结束
     */
    private Integer status;

    /**
     * 创建时间
     */
    @TableField("createAt")
    private Date createAt;


}
