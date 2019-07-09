package com.suyu.voting.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 投票候选人表
 * </p>
 *
 * @author Suyu
 * @since 2019-07-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Members implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 候选人昵称
     */
    private String name;

    /**
     * 投票项目id
     */
    @TableField("themeId")
    private Integer themeId;

    /**
     * 创建时间
     */
    @TableField("createAt")
    private Date createAt;


}
