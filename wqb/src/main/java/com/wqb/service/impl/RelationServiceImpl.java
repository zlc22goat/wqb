package com.wqb.service.impl;

import com.wqb.entity.Relation;
import com.wqb.mapper.RelationMapper;
import com.wqb.service.RelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lincheon
 * @since 2023-04-15
 */
@Service
public class RelationServiceImpl extends ServiceImpl<RelationMapper, Relation> implements RelationService {

    @Autowired
    private RelationMapper relationMapper;
    @Override
    public Boolean deleteByExamId(String id) {
        return relationMapper.deleteByExamId(id);
    }
}
