package com.wqb.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wqb.common.QueryPageParam;
import com.wqb.common.Result;
import com.wqb.dto.Params;
import com.wqb.entity.Exam;
import com.wqb.entity.Question;
import com.wqb.service.MenuService;
import com.wqb.service.QuestionService;
import com.wqb.vo.MasteryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lincheon
 * @since 2023-04-09
 */
@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/list")
    public List<Question> list() {
        return questionService.list();
    }

//    @PostMapping("/selectAll")
//    public Result selectAll(@RequestBody QueryPageParam query) {
//
//        Page<Question> questionPage = new Page<>();
//        questionPage.setCurrent(query.getPageNum());
//        questionPage.setSize(query.getPageSize());
//
//        IPage res = questionService.selectAll(questionPage);
//        return Result.suc(res.getRecords());
//    }

    @PostMapping("/updateQuestion")
    public Result updateQuestion(@RequestBody Question question) {
        question.setUpdateTime(new Date());
        question.setReview(0);
        return questionService.updateById(question) ? Result.suc(question) : Result.fail();
    }

    @PostMapping("/save")
    public Result save(@RequestBody Params param) {
        Question question = param.getQuestion();

        if (param.getList() != null) {
            String[] list = param.getList();
            Arrays.sort(list);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < list.length; i++) {
                if (list[i] != null) {
                    stringBuilder.append(list[i]);
                }
            }
            question.setAnswerOption(stringBuilder.toString());
        }

        Timestamp createTime = new Timestamp(new Date().getTime());
        question.setCreateTime(createTime);
        question.setUpdateTime(createTime);
        question.setMastery(0);
        question.setReview(0);

        return questionService.save(question) ? Result.suc() : Result.fail();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Params param) {
        Question question = param.getQuestion();

        if (param.getList() != null) {
            String[] list = param.getList();
            Arrays.sort(list);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < list.length; i++) {
                if (list[i] != null) {
                    stringBuilder.append(list[i]);
                }
            }
            question.setAnswerOption(stringBuilder.toString());
        }
        return questionService.updateById(question) ? Result.suc() : Result.fail();
    }

    @PostMapping("/updateReview")
    public Result updateReview(@RequestBody Params param) {
        Question question = param.getQuestion();

        Timestamp createTime = new Timestamp(new Date().getTime());
        question.setUpdateTime(createTime);
        question.setReview(0);
        return questionService.updateById(question) ? Result.suc() : Result.fail();
    }

    @GetMapping("/del")
    public Result del(@RequestParam String id) {
        return questionService.removeById(id) ? Result.suc() : Result.fail();
    }

    @PostMapping("/mod")
    public boolean mod(@RequestBody Question question) {
        return questionService.updateById(question);
    }

    @PostMapping("/findById")
    public Result findById(@RequestParam Integer id) {
        Question question = questionService.getById(id);
        return Result.suc(question);
    }

    @PostMapping("/getCheck")
    public Result getCheck(@RequestBody Question question) {
        String str = question.getAnswerOption();
        String[] strings = new String[str.length()];
        for (int i = 0; i < str.length(); i++) {
            strings[i] = String.valueOf(str.charAt(i));
        }
        return Result.suc(strings);
    }


    //新增或修改
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody Question question) {
        return questionService.saveOrUpdate(question);
    }

    @GetMapping("/delete")
    public boolean delete(Integer id) {
        return questionService.removeById(id);
    }

    /**
     * 根据各种参数查询分页
     * @param query
     * @return
     */
    @PostMapping("/selectByParam")
    public Result selectByParam(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String body = (String) param.get("body");
        String level = (String) param.get("level");
//        Integer courseId = (Integer) param.get("courseId");
        String type = (String) param.get("type");
        Integer studentId = (Integer) param.get("studentId");
        String mastery = (String) param.get("mastery");

        Page<Question> questionPage = new Page<>();
        questionPage.setCurrent(query.getPageNum());
        questionPage.setSize(query.getPageSize());

        LambdaQueryWrapper<Question> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (!"".equals(level)) {
            lambdaQueryWrapper.eq(Question::getLevel, level);
        }
        if (param.get("courseId") != "") {
            Integer courseId = (Integer) param.get("courseId");
            lambdaQueryWrapper.eq(Question::getCourseId, courseId);
        }
        if (!"".equals(type)) {
            lambdaQueryWrapper.eq(Question::getType, type);
        }
        if (studentId != null) {
            lambdaQueryWrapper.eq(Question::getStudentId, studentId);
        }
        if (!"".equals(mastery)) {
            lambdaQueryWrapper.eq(Question::getMastery, mastery);
        }
        if (!"".equals(body)) {
            lambdaQueryWrapper.like(Question::getBody, body);
        }
        lambdaQueryWrapper.orderByDesc(Question::getReview)
                .orderByDesc(Question::getUpdateTime)
                .orderByDesc(Question::getCreateTime);
        IPage result = questionService.selectQuestion(questionPage, lambdaQueryWrapper);

        return Result.suc(result.getRecords(), result.getTotal());
    }

    @GetMapping("/getMastery")
    public Result getMastery(@RequestParam String id) {
        LambdaQueryWrapper<Question> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Question::getStudentId, id)
                .groupBy(Question::getMastery);
        return Result.suc(questionService.getMastery(lambdaQueryWrapper));
    }

    @GetMapping("/getLevel")
    public Result getLevel(@RequestParam String id) {
        LambdaQueryWrapper<Question> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Question::getStudentId, id)
                .groupBy(Question::getLevel);
        return Result.suc(questionService.getLevel(lambdaQueryWrapper));
    }

    @GetMapping("/getCourse")
    public Result getCourse(@RequestParam String id) {
        LambdaQueryWrapper<Question> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Question::getStudentId, id)
                .groupBy(Question::getCourseId);
        return Result.suc(questionService.getCourse(lambdaQueryWrapper));
    }

    @GetMapping("/getReview")
    public Result getReview(@RequestParam String id) {
        LambdaQueryWrapper<Question> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Question::getStudentId, id)
                .groupBy(Question::getReview);
        return Result.suc(questionService.getReview(lambdaQueryWrapper));
    }

    @GetMapping("/getNewQue")
    public Result getNewQue(@RequestParam String id) {
        return Result.suc(questionService.getNewQue(id));
    }

    @GetMapping("/getUpdateQue")
    public Result getUpdateQue(@RequestParam String id) {
        return Result.suc(questionService.getUpdateQue(id));
    }
}

