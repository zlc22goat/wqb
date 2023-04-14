package com.wqb.controller;


import com.alibaba.fastjson.JSONObject;
import com.wqb.common.Result;
import com.wqb.dto.AnswerDto;
import com.wqb.dto.Params;
import com.wqb.entity.Answer;
import com.wqb.entity.Question;
import com.wqb.mapper.AnswerMapper;
import com.wqb.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.sql.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lincheon
 * @since 2023-04-14
 */
@RestController
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping("/save")
    public Result save(@RequestBody AnswerDto param) {
        Answer answer = new Answer();

        if (param.getStrings() != null) {
            String[] strings = param.getStrings();
            Arrays.sort(strings);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < strings.length; i++) {
                if (strings[i] != null) {
                    stringBuilder.append(strings[i]);
                }
            }
            String str = stringBuilder.toString();
            answer.setMyAnswerOption(str);
        }

        Integer questionId = param.getId();
        Timestamp createTime = new Timestamp(new Date().getTime());
        answer.setCreateTime(createTime);
        answer.setQuestionId(questionId);

        return answerService.save(answer) ? Result.suc(answer) : Result.fail();
    }

    @PostMapping("/savePic")
    public Result savePic(@RequestBody Answer answer) {
//        Answer answer = param.getAnswer();

//        Integer questionId = param.getId();
        Timestamp createTime = new Timestamp(new Date().getTime());
        answer.setCreateTime(createTime);
//        answer.setQuestionId(questionId);

        return answerService.save(answer) ? Result.suc(answer) : Result.fail();
    }

}

