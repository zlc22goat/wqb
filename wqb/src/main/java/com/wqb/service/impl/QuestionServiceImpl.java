package com.wqb.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wqb.entity.Question;
import com.wqb.mapper.QuestionMapper;
import com.wqb.service.QuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

//    @Override
//    public IPage selectAll(IPage<Question> page) {
//        return questionMapper.selectAll(page);
//    }
}
