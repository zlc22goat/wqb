package com.wqb.controller;

import com.wqb.common.Result;
import com.wqb.entity.Relation;
import com.wqb.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/relation")
public class RelationController {

    @Autowired
    private RelationService relationService;

    /**
     * 传入试卷id，删除与其相关所有relation
     * @param id
     * @return
     */
    @GetMapping("/delete")
    public Result delete(@RequestParam String id) {
        List list = relationService.lambdaQuery().eq(Relation::getExamId,id).list();
        if (list.size() != 0) {
            return relationService.deleteByExamId(id) ? Result.suc(id) : Result.fail();
        }
        return Result.suc(id);
    }

}

