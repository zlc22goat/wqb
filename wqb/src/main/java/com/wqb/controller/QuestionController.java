package com.wqb.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wqb.common.QueryPageParam;
import com.wqb.common.Result;
import com.wqb.entity.Menu;
import com.wqb.entity.Question;
import com.wqb.service.MenuService;
import com.wqb.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/selectAll")
    public Result selectAll(@RequestBody QueryPageParam query) {

        Page<Question> questionPage = new Page<>();
        questionPage.setCurrent(query.getPageNum());
        questionPage.setSize(query.getPageSize());

        IPage res = questionService.selectAll(questionPage);
        return Result.suc(res.getRecords());
    }

    @PostMapping("/save")
    public Result save(@RequestBody Question question) {
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
        Integer level = (Integer) param.get("level");
        Integer courseId = (Integer) param.get("courseId");
        Integer type = (Integer) param.get("type");
        Integer studentId = (Integer) param.get("studentId");
        Integer mastery = (Integer) param.get("mastery");

        Page<Question> questionPage = new Page<>();
        questionPage.setCurrent(query.getPageNum());
        questionPage.setSize(query.getPageSize());

        LambdaQueryWrapper<Question> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (level != null) {
            lambdaQueryWrapper.eq(Question::getLevel, level);
        } else if (courseId != null) {
            lambdaQueryWrapper.eq(Question::getCourseId, courseId);
        } else if (type != null) {
            lambdaQueryWrapper.eq(Question::getType, type);
        } else if (studentId != null) {
            lambdaQueryWrapper.eq(Question::getStudentId, studentId);
        } else if (mastery != null) {
            lambdaQueryWrapper.eq(Question::getMastery, mastery);
        }
        IPage result = questionService.selectQuestion(questionPage, lambdaQueryWrapper);

        return Result.suc(result.getRecords(), result.getTotal());
    }
}

