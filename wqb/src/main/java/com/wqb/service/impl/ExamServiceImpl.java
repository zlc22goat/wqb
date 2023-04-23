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
import java.util.Map;

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

    @Override
    public List<Map<Integer, Integer>> getState(String id) {
        return examMapper.getState(id);
    }

    @Override
    public List<Map<Integer, Integer>> getMark(String id) {
        return examMapper.getMark(id);
    }

    @Override
    public List<Map<Integer, Integer>> getNewExam(String id) {
        return examMapper.getNewExam(id);
    }

    @Override
    public List<Map<String, Integer>> getAllMark() {
        return examMapper.getAllMark();
    }
}
