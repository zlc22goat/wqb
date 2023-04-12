package com.wqb.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wqb.common.QueryPageParam;
import com.wqb.common.Result;
import com.wqb.entity.Course;
import com.wqb.entity.Course;
import com.wqb.service.CourseService;
import com.wqb.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lincheon
 * @since 2023-04-03
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/list")
    public Result list() {
        return Result.suc(courseService.list());
    }

    @PostMapping("/save")
    public Result save(@RequestBody Course course) {
        return courseService.save(course) ? Result.suc() : Result.fail();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Course course) {
        return courseService.updateById(course) ? Result.suc() : Result.fail();
    }

//    @GetMapping("/del")
//    public Result del(@RequestParam String id) {
//        return courseService.removeById(id) ? Result.suc() : Result.fail();
//    }

    @PostMapping("/mod")
    public boolean mod(@RequestBody Course course) {
        return courseService.updateById(course);
    }

    @GetMapping("/findByNo")
    public Result findByNo(@RequestParam String no){
        List list = courseService.lambdaQuery().eq(Course::getCId,no).list();
        return list.size()>0?Result.suc(list):Result.fail();
    }

    //新增或修改
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody Course course) {
        return courseService.saveOrUpdate(course);
    }

    @GetMapping("/delete")
    public boolean delete(Integer id) {
        return courseService.removeById(id);
    }

    @GetMapping("/del")
    public Result del(@RequestParam String id) {
        return courseService.removeById(id) ? Result.suc() : Result.fail();
    }

//    @PostMapping("/listP")
//    public Result listP(@RequestBody Course course) {
//        LambdaQueryWrapper<Course> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        if(StringUtils.isNotBlank(course.getCName())) {
//            lambdaQueryWrapper.like(Course::getCName, course.getCName());// 模糊查询
//        }
//        return Result.suc(courseService.list(lambdaQueryWrapper));
//    }

    /**
     * 查询全部课程
     * @param query
     * @return
     */
    @PostMapping("/selectAll")
    public Result selectAll(@RequestBody QueryPageParam query) {

        Page<Course> coursePage = new Page<>();
        coursePage.setCurrent(query.getPageNum());
        coursePage.setSize(query.getPageSize());

        IPage result = courseService.page(coursePage);

        System.out.println("total = " + result.getTotal());

        return Result.suc(result.getRecords(), result.getTotal());
    }

    /**
     * 根据课程名模糊查询
     * @param query
     * @return
     */
    @PostMapping("/selectByName")
    public Result selectByName(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name = (String)param.get("cname");
        Page<Course> coursePage = new Page<>();
        coursePage.setCurrent(query.getPageNum());
        coursePage.setSize(query.getPageSize());

        IPage result = null;

        if ("".equals(name)) {
            result = courseService.page(coursePage);
        } else {
            LambdaQueryWrapper<Course> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.like(Course::getCName, name);
            result = courseService.page(coursePage, lambdaQueryWrapper);
        }

        System.out.println("total = " + result.getTotal());

        return Result.suc(result.getRecords(), result.getTotal());
    }

}

