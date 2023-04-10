package com.wqb.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.QEncoderStream;
import com.wqb.entity.Question;
import com.wqb.mapper.QuestionMapper;
import com.wqb.service.QuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

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

    @Override
    public IPage selectAll(IPage<Question> page) {
        return questionMapper.selectAll(page);
    }
}
