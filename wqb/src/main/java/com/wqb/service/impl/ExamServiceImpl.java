package com.wqb.service.impl;

import com.wqb.entity.Exam;
import com.wqb.entity.Question;
import com.wqb.mapper.ExamMapper;
import com.wqb.service.ExamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wqb.vo.QuestionCourseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lincheon
 * @since 2023-04-15
 */
@Service
public class ExamServiceImpl extends ServiceImpl<ExamMapper, Exam> implements ExamService {

    @Autowired
    private ExamMapper examMapper;

    @Override
    public List<QuestionCourseVo> selectDetail(String id) {
        return examMapper.selectDetail(id);
    }

    @Override
    public List<Question> selectAllQuestion(String id) {
        return examMapper.selectAllQuestion(id);
    }

    @Override
    public Integer[] selectQuestionId(String id) {
        return examMapper.selectQuestionId(id);
    }
}
