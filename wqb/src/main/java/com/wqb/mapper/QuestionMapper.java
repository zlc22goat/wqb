package com.wqb.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.wqb.entity.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wqb.vo.QuestionCourseVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lincheon
 * @since 2023-04-09
 */
@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
    IPage selectQuestion(IPage<Question> page, @Param(Constants.WRAPPER) Wrapper<QuestionCourseVo> ew);

    IPage selectAll(IPage<Question> page);
}
