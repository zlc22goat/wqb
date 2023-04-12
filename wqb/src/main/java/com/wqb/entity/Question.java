package com.wqb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author lincheon
 * @since 2023-04-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Question对象", description="")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "题干")
    private String body;

    private String bodyPic;

    @ApiModelProperty(value = "选项A")
    @TableField("optionA")
    private String optiona;

    private String optionaPic;

    @ApiModelProperty(value = "选项B")
    @TableField("optionB")
    private String optionb;

    private String optionbPic;

    @ApiModelProperty(value = "选项C")
    @TableField("optionC")
    private String optionc;

    private String optioncPic;

    @ApiModelProperty(value = "选项D")
    @TableField("optionD")
    private String optiond;

    private String optiondPic;

    @ApiModelProperty(value = "选择题答案   0123代表ABCD")
    private String answerOption;

    @ApiModelProperty(value = "填空解答题答案")
    private String answer;

    @ApiModelProperty(value = "填空解答题答案图片描述")
    private String answerPic;

    @ApiModelProperty(value = "我的选择题答案  0123代表ABCD")
    private String myAnswerOption;

    @ApiModelProperty(value = "我的填空解答题答案")
    private String myAnswer;

    @ApiModelProperty(value = "我的填空解答题答案图片描述")
    private String myAnswerPic;

    @ApiModelProperty(value = "解析")
    private String detail;

    @ApiModelProperty(value = "解析图片")
    private String detailPic;

    @ApiModelProperty(value = "难度")
    private Integer level;

    @ApiModelProperty(value = "属于何种学科")
    private Integer courseId;

    @ApiModelProperty(value = "0单选  1多选  2填空  3解答")
    private Integer type;

    @ApiModelProperty(value = "属于哪位学生的错题")
    private Integer studentId;

    @ApiModelProperty(value = "题目备注")
    private String remark;

    @ApiModelProperty(value = "掌握程度")
    private Integer mastery;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "GMT+8") //从数据库读出日期格式时，进行转换的规则
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")//接受从前端传入的日期格式，映射到java类日期属性的规则
    private Date createTime;

    @ApiModelProperty(value = "最后更新时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "GMT+8") //从数据库读出日期格式时，进行转换的规则
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")//接受从前端传入的日期格式，映射到java类日期属性的规则
    private Date updateTime;


}
