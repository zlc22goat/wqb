package com.wqb.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wqb.common.Result;
import com.wqb.dto.AnswerDto;
import com.wqb.dto.Params;
import com.wqb.entity.Answer;
import com.wqb.entity.Question;
import com.wqb.entity.Student;
import com.wqb.mapper.AnswerMapper;
import com.wqb.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        Answer answer;
        if (param.getAnswer() != null) {
            answer = param.getAnswer();
        } else {
            answer = new Answer();
        }

        // 处理选择题
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

        if (param.getId() != null) {
            Integer questionId = param.getId();
            answer.setQuestionId(questionId);
        }

        Timestamp createTime = new Timestamp(new Date().getTime());
        answer.setCreateTime(createTime);

        return answerService.save(answer) ? Result.suc(answer) : Result.fail();
    }

//    @PostMapping("/saveAnswerList")
//    public Result saveAnswerList(@RequestBody AnswerDto param) {
//        List<Answer> answerList = new ArrayList<>();
//
//        if (param.getStrings() != null) {
//            String[] strings = param.getStrings();
//            Arrays.sort(strings);
//            StringBuilder stringBuilder = new StringBuilder();
//            for (int i = 0; i < strings.length; i++) {
//                if (strings[i] != null) {
//                    stringBuilder.append(strings[i]);
//                }
//            }
//            String str = stringBuilder.toString();
//            answer.setMyAnswerOption(str);
//        }
//
//        List<Question> questionList = param.getQuestionList();
//        for (Question question: questionList) {
//            if (question != null) {
//                Answer answer = new Answer();
//
//                answer.setCreateTime(new Date());
//                answer.setQuestionId(question.getId());
//            }
//        }
//
//        Integer questionId = param.getId();
//        Timestamp createTime = new Timestamp(new Date().getTime());
//        answer.setCreateTime(createTime);
//        answer.setQuestionId(questionId);
//
//        return answerService.save(answer) ? Result.suc(answer) : Result.fail();
//    }

    @PostMapping("/savePic")
    public Result savePic(@RequestBody Answer answer) {
//        Answer answer = param.getAnswer();

//        Integer questionId = param.getId();
        Timestamp createTime = new Timestamp(new Date().getTime());
        answer.setCreateTime(createTime);
//        answer.setQuestionId(questionId);

        return answerService.save(answer) ? Result.suc(answer) : Result.fail();
    }

    @PostMapping("/selectAllAnswer")
    public Result selectAllAnswer(@RequestParam String id) {
        List<Answer> answerList = answerService.lambdaQuery().
                eq(Answer::getQuestionId, id)
                .orderByDesc(Answer::getCreateTime).list();
        return answerList.size() > 0 ? Result.suc(answerList) : Result.fail();
    }

}

