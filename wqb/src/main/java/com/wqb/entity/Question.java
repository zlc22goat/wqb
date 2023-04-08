package com.wqb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.sql.Blob;
import com.baomidou.mybatisplus.annotation.TableField;
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
@ApiModel(value="Question对象", description="")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "q_id", type = IdType.AUTO)
    private Integer qId;

    @ApiModelProperty(value = "题干")
    private Blob qBody;

    @ApiModelProperty(value = "选项A")
    @TableField("optionA")
    private Blob optiona;

    @ApiModelProperty(value = "选项B")
    @TableField("optionB")
    private Blob optionb;

    @ApiModelProperty(value = "选项C")
    @TableField("optionC")
    private Blob optionc;

    @ApiModelProperty(value = "选项D")
    @TableField("optionD")
    private Blob optiond;

    @ApiModelProperty(value = "答案")
    private Blob answer;

    @ApiModelProperty(value = "解析")
    private Blob detail;

    @ApiModelProperty(value = "难度")
    private Integer level;

    @ApiModelProperty(value = "属于何种学科")
    @TableField("courseId")
    private Integer courseid;

    @ApiModelProperty(value = "0单选  1多选  2填空  3解答")
    private Integer type;

    @ApiModelProperty(value = "属于哪位学生的错题")
    @TableField("studentId")
    private Integer studentid;

    @ApiModelProperty(value = "题目备注")
    private String remark;


}
