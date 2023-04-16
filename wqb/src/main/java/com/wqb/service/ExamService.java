package com.wqb.service;

import com.wqb.entity.Exam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wqb.entity.Question;
import com.wqb.vo.QuestionCourseVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lincheon
 * @since 2023-04-15
 */
public interface ExamService extends IService<Exam> {
    List<QuestionCourseVo> selectDetail(String id);

    List<Question> selectAllQuestion(String id);

    Integer[] selectQuestionId(String id);
}
