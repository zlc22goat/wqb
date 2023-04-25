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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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
@ApiModel(value="Student对象", description="")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "s_id", type = IdType.AUTO)
    private Integer sId;

    @ApiModelProperty(value = "学生账号")
    private String sNo;

    @ApiModelProperty(value = "学生姓名")
    private String sName;

    @ApiModelProperty(value = "学生登录密码")
    private String sPassword;

    @ApiModelProperty(value = "性别")
    private Integer gender;

    @ApiModelProperty(value = "专业")
    private String major;

    @ApiModelProperty(value = "电话号码")
    private String phone;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "GMT+8") //从数据库读出日期格式时，进行转换的规则
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")//接受从前端传入的日期格式，映射到java类日期属性的规则
    private Date createTime;

    @ApiModelProperty(value = "年级id")
    @TableField("gradeId")
    private Integer gradeid;

    @ApiModelProperty(value = "角色id")
    @TableField("roleId")
    private Integer roleid;

    private Integer squadId;
}
