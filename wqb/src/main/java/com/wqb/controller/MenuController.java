package com.wqb.controller;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.wqb.common.Result;
import com.wqb.entity.Menu;
import com.wqb.entity.Student;
import com.wqb.mapper.MenuMapper;
import com.wqb.service.MenuService;
import com.wqb.utils.MenuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lincheon
 * @since 2023-04-08
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    public Result list(@RequestParam String roleId) {
        List list = menuService.lambdaQuery().like(Menu::getMenuright, roleId).list();
        return Result.suc(list);
    }

    /**
     * 从数据库中获取所有的菜单数据后，转为树形结构
     * @author 云胡
     * @return 树形菜单结构数据
     */
    @GetMapping(value = "/getAllMenu")
    @ResponseBody
    public Result getAllSupplier(@RequestParam String roleId){
        // 先获取所有的数据表数据
        List<Menu> allMenuTreeVoList = menuService.lambdaQuery().like(Menu::getMenuright, roleId).list();

        List<Menu> menuTreeVOTreeList = MenuUtils.toTree(allMenuTreeVoList);

        if (CollectionUtils.isNotEmpty(menuTreeVOTreeList)) {
            return Result.suc(menuTreeVOTreeList);
        }else {
            return null;
        }
    }

}

