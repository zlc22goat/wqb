package com.wqb.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wqb.entity.Question;
import com.wqb.mapper.QuestionMapper;
import com.wqb.service.QuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lincheon
 * @since 2023-04-09
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

    @Resource
    private QuestionMapper questionMapper;
    @Override
    public IPage selectQuestion(IPage<Question> page, Wrapper wrapper) {
        return questionMapper.selectQuestion(page, wrapper);
    }

    @Override
    public List getMastery(Wrapper wrapper) {
        return questionMapper.getMastery(wrapper);
    }

    @Override
    public List getLevel(Wrapper wrapper) {
        return questionMapper.getLevel(wrapper);
    }

    @Override
    public List getCourse(Wrapper wrapper) {
        return questionMapper.getCourse(wrapper);
    }

    @Override
    public List getReview(Wrapper wrapper) {
        return questionMapper.getReview(wrapper);
    }

    @Override
    public List<Map<Integer, Integer>> getNewQue(String id) {
        return questionMapper.getNewQue(id);
    }

    @Override
    public List<Map<Integer, Integer>> getUpdateQue(String id) {
        return questionMapper.getUpdateQue(id);
    }
//    @Override
//    public IPage selectAll(IPage<Question> page) {
//        return questionMapper.selectAll(page);
//    }
}
