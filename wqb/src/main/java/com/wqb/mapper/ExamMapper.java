package com.wqb.mapper;

import com.wqb.entity.Exam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wqb.vo.QuestionCourseVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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
    List<QuestionCourseVo> selectDetail(Integer id);
}
