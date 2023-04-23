package com.wqb.mapper;

import com.wqb.entity.Exam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wqb.entity.Question;
import com.wqb.vo.QuestionCourseVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lincheon
 * @since 2023-04-15
 */
@Mapper
public interface ExamMapper extends BaseMapper<Exam> {
    List<QuestionCourseVo> selectDetail(String id);

    List<Question> selectAllQuestion(String id);

    Integer[] selectQuestionId(String id);

    List<Map<Integer, Integer>> getState(String id);

    List<Map<Integer, Integer>> getMark(String id);

    List<Map<Integer, Integer>> getNewExam(String id);
}
