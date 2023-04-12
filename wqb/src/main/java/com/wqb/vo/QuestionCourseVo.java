package com.wqb.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author LinCheon
 * @date 2023/4/10 13:39
 * @description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class QuestionCourseVo {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String body;

    private String bodyPic;

    @TableField("optionA")
    private String optiona;

    private String optionaPic;

    @TableField("optionB")
    private String optionb;

    private String optionbPic;

    @TableField("optionC")
    private String optionc;

    private String optioncPic;

    @TableField("optionD")
    private String optiond;

    private String optiondPic;

    private String answerOption;

    private String answer;

    private String answerPic;

    private String myAnswerOption;

    private String myAnswer;

    private String myAnswerPic;

    private String detail;

    private String detailPic;

    private Integer level;

    private String cName;

    private Integer type;

    private Integer studentId;

    private String remark;

    private Integer mastery;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "GMT+8") //从数据库读出日期格式时，进行转换的规则
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")//接受从前端传入的日期格式，映射到java类日期属性的规则
    private Date createTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "GMT+8") //从数据库读出日期格式时，进行转换的规则
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")//接受从前端传入的日期格式，映射到java类日期属性的规则
    private Date updateTime;
}
