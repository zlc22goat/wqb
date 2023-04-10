package com.wqb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

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
@ApiModel(value="Message对象", description="")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "message_id", type = IdType.AUTO)
    private Integer messageId;

    @ApiModelProperty(value = "消息内容")
    private String context;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") //从数据库读出日期格式时，进行转换的规则
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//接受从前端传入的日期格式，映射到java类日期属性的规则
    private Date createTime;


}
