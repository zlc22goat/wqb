package com.wqb.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wqb.common.QueryPageParam;
import com.wqb.common.Result;
import com.wqb.dto.Params;
import com.wqb.entity.Question;
import com.wqb.service.MenuService;
import com.wqb.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;
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

    @Autowired
    private MenuService menuService;

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

    @PostMapping("/save")
    public Result save(@RequestBody Params param) {
        Boolean[] list = param.getList();
        Question question = param.getQuestion();

        // 选择题
        if (list != null) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < 4; i++) {
                if (list[i]) {
                    switch (i) {
                        case 0: stringBuilder.append("A"); break;
                        case 1: stringBuilder.append("B"); break;
                        case 2: stringBuilder.append("C"); break;
                        case 3: stringBuilder.append("D"); break;
                    }

                }
            }
            question.setAnswerOption(stringBuilder.toString());
        }

        Timestamp createTime = new Timestamp(new Date().getTime());
        question.setCreateTime(createTime);
        question.setUpdateTime(createTime);
        question.setMastery(0);

        return questionService.save(question) ? Result.suc() : Result.fail();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Question question) {
        Timestamp createTime = new Timestamp(new Date().getTime());
        question.setUpdateTime(createTime);
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

//    @GetMapping("/findByNo")
//    public Result findByNo(@RequestParam String no){
//        List list = questionService.lambdaQuery().eq(Question::getSNo,no).list();
//        return list.size()>0?Result.suc(list):Result.fail();
//    }

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
        String courseId = (String) param.get("courseId");
        String type = (String) param.get("type");
        String studentId = (String) param.get("studentId");
        String mastery = (String) param.get("mastery");

        Page<Question> questionPage = new Page<>();
        questionPage.setCurrent(query.getPageNum());
        questionPage.setSize(query.getPageSize());

        LambdaQueryWrapper<Question> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (!"".equals(level)) {
            lambdaQueryWrapper.eq(Question::getLevel, level);
        } else if (!"".equals(courseId)) {
            lambdaQueryWrapper.eq(Question::getCourseId, courseId);
        } else if (!"".equals(type)) {
            lambdaQueryWrapper.eq(Question::getType, type);
        } else if (!"".equals(studentId)) {
            lambdaQueryWrapper.eq(Question::getStudentId, studentId);
        } else if (!"".equals(mastery)) {
            lambdaQueryWrapper.eq(Question::getMastery, mastery);
        } else if (!"".equals(body)) {
            lambdaQueryWrapper.like(Question::getBody, body);
        }
        IPage result = questionService.selectQuestion(questionPage, lambdaQueryWrapper);

        return Result.suc(result.getRecords(), result.getTotal());
    }
}

