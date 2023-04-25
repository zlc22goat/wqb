package com.wqb.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wqb.common.QueryPageParam;
import com.wqb.common.Result;
import com.wqb.entity.Squad;
import com.wqb.service.SquadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lincheon
 * @since 2023-04-24
 */
@RestController
@RequestMapping("/squad")
public class SquadController {

    @Autowired
    private SquadService squadService;

    @GetMapping("/list")
    public Result list() {
        return Result.suc(squadService.list());
    }

    @PostMapping("/save")
    public Result save(@RequestBody Squad squad) {
        return squadService.save(squad) ? Result.suc() : Result.fail();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Squad squad) {
        return squadService.updateById(squad) ? Result.suc() : Result.fail();
    }

    @PostMapping("/mod")
    public boolean mod(@RequestBody Squad squad) {
        return squadService.updateById(squad);
    }

    @GetMapping("/del")
    public Result del(@RequestParam String id) {
        return squadService.removeById(id) ? Result.suc() : Result.fail();
    }

    @GetMapping("/delete")
    public boolean delete(Integer id) {
        return squadService.removeById(id);
    }

    /**
     * 根据名模糊查询
     * @param query
     * @return
     */
    @PostMapping("/selectByName")
    public Result selectByName(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name = (String)param.get("name");
        Page<Squad> squadPage = new Page<>();
        squadPage.setCurrent(query.getPageNum());
        squadPage.setSize(query.getPageSize());

        LambdaQueryWrapper<Squad> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Squad::getName, name);

        IPage result = squadService.page(squadPage, lambdaQueryWrapper);

        System.out.println("total = " + result.getTotal());

        return Result.suc(result.getRecords(), result.getTotal());
    }

}

