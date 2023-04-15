package com.wqb.service;

import com.wqb.entity.Relation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lincheon
 * @since 2023-04-15
 */
public interface RelationService extends IService<Relation> {
    Boolean deleteByExamId(String id);
}
