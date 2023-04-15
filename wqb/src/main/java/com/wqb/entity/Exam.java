package com.wqb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author lincheon
 * @since 2023-04-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Exam对象", description="")
public class Exam implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "试卷名称")
    private String name;

    private Integer studentId;

    private Date createTime;

    @ApiModelProperty(value = "题目数")
    private Integer number;

    @ApiModelProperty(value = "所得分数")
    private Integer mark;

    @ApiModelProperty(value = "1完成 0未完成")
    private Integer state;


}
