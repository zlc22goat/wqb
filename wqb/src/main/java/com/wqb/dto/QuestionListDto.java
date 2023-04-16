package com.wqb.dto;

import com.wqb.entity.Exam;
import com.wqb.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author LinCheon
 * @date 2023/4/15 20:11
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionListDto {
    private Exam exam;

    private List<Question> questionList;
}
