package com.wqb.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author LinCheon
 * @date 2023/4/22 23:02
 * @description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class LevelVo {
    private Integer level;

    private Integer count;
}
