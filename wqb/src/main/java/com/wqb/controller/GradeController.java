package com.wqb.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wqb.common.QueryPageParam;
import com.wqb.common.Result;
import com.wqb.entity.Grade;
import com.wqb.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/grade")
public class GradeController {
    @Autowired
    private GradeService gradeService;

    @GetMapping("/list")
    public Result list() {
        List<Grade> list = gradeService.list();
        return Result.suc(list);
    }

    @PostMapping("/save")
    public Result save(@RequestBody Grade grade) {
        return gradeService.save(grade) ? Result.suc() : Result.fail();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Grade grade) {
        return gradeService.updateById(grade) ? Result.suc() : Result.fail();
    }

//    @GetMapping("/del")
//    public Result del(@RequestParam String id) {
//        return gradeService.removeById(id) ? Result.suc() : Result.fail();
//    }

    @PostMapping("/mod")
    public boolean mod(@RequestBody Grade grade) {
        return gradeService.updateById(grade);
    }

    @GetMapping("/findByNo")
    public Result findByNo(@RequestParam String no){
        List list = gradeService.lambdaQuery().eq(Grade::getGradeId,no).list();
        return list.size()>0?Result.suc(list):Result.fail();
    }

    //新增或修改
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody Grade grade) {
        return gradeService.saveOrUpdate(grade);
    }

    @GetMapping("/delete")
    public boolean delete(Integer id) {
        return gradeService.removeById(id);
    }

//    @PostMapping("/listP")
//    public Result listP(@RequestBody Grade grade) {
//        LambdaQueryWrapper<Grade> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        if(StringUtils.isNotBlank(grade.getCName())) {
//            lambdaQueryWrapper.like(Grade::getCName, grade.getCName());// 模糊查询
//        }
//        return Result.suc(gradeService.list(lambdaQueryWrapper));
//    }

    /**
     * 查询全部年级
     * @param query
     * @return
     */
    @PostMapping("/selectAll")
    public Result selectAll(@RequestBody QueryPageParam query) {

        Page<Grade> gradePage = new Page<>();
        gradePage.setCurrent(query.getPageNum());
        gradePage.setSize(query.getPageSize());

        IPage result = gradeService.page(gradePage);

        System.out.println("total = " + result.getTotal());

        return Result.suc(result.getRecords(), result.getTotal());
    }

//    /**
//     * 根据名模糊查询
//     * @param query
//     * @return
//     */
//    @PostMapping("/selectByName")
//    public Result selectByName(@RequestBody QueryPageParam query) {
//        HashMap param = query.getParam();
//        String name = (String)param.get("cname");
//        Page<Grade> gradePage = new Page<>();
//        gradePage.setCurrent(query.getPageNum());
//        gradePage.setSize(query.getPageSize());
//
//        LambdaQueryWrapper<Grade> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        lambdaQueryWrapper.like(Grade::getGradeName, name);
//
//        IPage result = gradeService.page(gradePage, lambdaQueryWrapper);
//
//        System.out.println("total = " + result.getTotal());
//
//        return Result.suc(result.getRecords(), result.getTotal());
//    }
}

