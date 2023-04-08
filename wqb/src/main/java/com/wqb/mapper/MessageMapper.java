package com.wqb.mapper;

import com.wqb.entity.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lincheon
 * @since 2023-04-03
 */
@Mapper
public interface MessageMapper extends BaseMapper<Message> {

}
