package com.wqb.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author LinCheon
 * @date 2023/4/23 1:05
 * @description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class NewQueVo {
    private String month;

    private Integer count;
}
