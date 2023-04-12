package com.wqb.dto;

import com.wqb.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author LinCheon
 * @date 2023/4/12 1:04
 * @description:
 */
@Data
public class Params {
    private Question question;
    private Boolean[] list;
}
