package com.wqb.service.impl;

import com.wqb.entity.Question;
import com.wqb.mapper.QuestionMapper;
import com.wqb.service.QuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lincheon
 * @since 2023-04-03
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

}
