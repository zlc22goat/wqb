package com.wqb.service;

import com.wqb.entity.Exam;
import com.baomidou.mybatisplus.extension.service.IService;
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
    List<QuestionCourseVo> selectDetail(Integer id);
}
