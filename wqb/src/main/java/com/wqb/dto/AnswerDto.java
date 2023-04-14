package com.wqb.dto;

import com.wqb.entity.Answer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LinCheon
 * @date 2023/4/14 12:17
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDto {
    private String[] strings;

    private Integer id;

    private Answer answer;
}
