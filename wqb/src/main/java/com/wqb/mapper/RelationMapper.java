package com.wqb.mapper;

import com.wqb.entity.Relation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lincheon
 * @since 2023-04-15
 */
@Mapper
public interface RelationMapper extends BaseMapper<Relation> {
    Boolean deleteByExamId(String id);
}
