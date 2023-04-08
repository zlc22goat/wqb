package com.wqb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2023-04-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Grade对象", description="")
public class Grade implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "年级id")
      @TableId(value = "grade_id", type = IdType.AUTO)
    private Integer gradeId;

    @ApiModelProperty(value = "年级")
    private String gradeName;


}
