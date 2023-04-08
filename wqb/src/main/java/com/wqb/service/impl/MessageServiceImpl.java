package com.wqb.service.impl;

import com.wqb.entity.Message;
import com.wqb.mapper.MessageMapper;
import com.wqb.service.MessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lincheon
 * @since 2023-04-03
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Resource
    private MessageMapper messageMapper;

}
