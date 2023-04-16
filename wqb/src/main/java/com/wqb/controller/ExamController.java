package com.wqb.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wqb.common.QueryPageParam;
import com.wqb.common.Result;
import com.wqb.dto.QuestionListDto;
import com.wqb.entity.Exam;
import com.wqb.entity.Question;
import com.wqb.entity.Relation;
import com.wqb.service.ExamService;
import com.wqb.service.RelationService;
import com.wqb.vo.QuestionCourseVo;
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
 * @since 2023-04-15
 */
@RestController
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private ExamService examService;

    @Autowired
    private RelationService relationService;

    /**
     * 创建一张空白的试卷，只有创建的时间，主要是要拿到试卷的id，好建立relation
     * @return
     */
    @GetMapping("/create")
    public Result create() {
        Exam exam = new Exam();
        Timestamp createTime = new Timestamp(new Date().getTime());
        exam.setCreateTime(createTime);
        exam.setState(0);
        return examService.save(exam) ? Result.suc(exam) : Result.fail();
    }

    @PostMapping("/save")
    public Result save(@RequestBody Exam exam) {
        Timestamp createTime = new Timestamp(new Date().getTime());
        exam.setCreateTime(createTime);
        return examService.save(exam) ? Result.suc() : Result.fail();
    }

    @PostMapping("/update")
    public Result update(@RequestBody QuestionListDto questionListExam) {
        Exam exam = questionListExam.getExam();

        if (questionListExam.getQuestionList() != null) {
            int count = 0;
            List<Question> questionList  = questionListExam.getQuestionList();
            for (Question q: questionList) {
                if (q != null) {
                    count++;
                    Relation relation = new Relation();
                    relation.setExamId(exam.getId());
                    relation.setQuestionId(q.getId());
                    relationService.save(relation);
                }
            }
            exam.setNumber(count);
        }

        return examService.updateById(exam) ? Result.suc(exam) : Result.fail();
    }

    @GetMapping("/del")
    public Result del(@RequestParam String id) {
        return examService.removeById(id) ? Result.suc() : Result.fail();
    }


    /**
     * 查询所有试卷信息，不包含详细什么题目
     * @param query
     * @return
     */
    @PostMapping("/selectByParam")
    public Result selectByParam(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name = (String)param.get("name");
        String state = (String) param.get("state");
        Integer studentId = (Integer) param.get("studentId");

        Page<Exam> examPage = new Page<>();
        examPage.setCurrent(query.getPageNum());
        examPage.setSize(query.getPageSize());

        LambdaQueryWrapper<Exam> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Exam::getStudentId, studentId);
        if (!state.equals("")) {
            lambdaQueryWrapper.eq(Exam::getState, state);
        }
        if (!name.equals("")) {
            lambdaQueryWrapper.like(Exam::getName, name);// 用name做模糊查询
        }
        lambdaQueryWrapper.orderByDesc(Exam::getCreateTime);

        IPage result = examService.page(examPage, lambdaQueryWrapper);

        return Result.suc(result.getRecords(), result.getTotal());
    }

    /**
     * 查询某试卷的所有题目信息
     * @param id
     * @return
     */
    @GetMapping("/selectDetail")
    public Result selectDetail(@RequestParam String id) {
        return Result.suc(examService.selectDetail(id));
    }

    @GetMapping("/selectQuestionId")
    public Result selectQuestionId(@RequestParam String id) {
        return Result.suc(examService.selectQuestionId(id));
    }
}

