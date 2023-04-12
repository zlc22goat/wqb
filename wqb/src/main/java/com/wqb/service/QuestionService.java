package com.wqb.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wqb.entity.Question;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lincheon
 * @since 2023-04-09
 */
public interface QuestionService extends IService<Question> {
    IPage selectQuestion(IPage<Question> page, Wrapper wrapper);

//    IPage selectAll(IPage<Question> page);
}
