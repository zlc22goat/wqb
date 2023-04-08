package com.wqb.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wqb.entity.Student;
import io.swagger.annotations.ApiModel;
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
 * @date 2023/4/3 16:31
 * @description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class StudentGradeVo {
    @TableId(value = "s_id", type = IdType.AUTO)
    private Integer sId;
    private String sNo;
    private String sName;
    private Integer gender;
    private String major;
    private String phone;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "GMT+8") //从数据库读出日期格式时，进行转换的规则
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")//接受从前端传入的日期格式，映射到java类日期属性的规则
    private Date createTime;
    private String gradeName;
    @TableField("roleId")
    private Integer roleid;

}
