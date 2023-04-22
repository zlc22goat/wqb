package com.wqb.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wqb.entity.Question;
import com.wqb.vo.MasteryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    List<Map<Integer, Integer>> getMastery(Wrapper wrapper);

    List<Map<Integer, Integer>> getLevel(Wrapper wrapper);

    List<Map<Integer, Integer>> getCourse(Wrapper wrapper);

    List<Map<Integer, Integer>> getReview(Wrapper wrapper);

    List<Map<Integer, Integer>> getNewQue(String id);

    List<Map<Integer, Integer>> getUpdateQue(String id);
}
