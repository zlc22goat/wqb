package com.wqb.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author LinCheon
 * @date 2023/4/23 13:20
 * @description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class NewExamVo {
    private String month;

    private Integer monthCount;

    private Integer state;

    private Integer stateCount;

    private String markRemark;

    private Integer markCount;
}
