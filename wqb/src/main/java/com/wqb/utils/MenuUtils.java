package com.wqb.utils;

import com.wqb.entity.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LinCheon
 * @date 2023/4/10 17:14
 * @description:
 */
public class MenuUtils {

    /**
     * 所有的菜单
     */
    private static List<Menu> allList = null;

    /**
     * 转换为树形结构
     * @param list 所有的节点
     * @return 树结构菜单
     */
    public static List<Menu> toTree(List<Menu> list) {
        allList = new ArrayList<>(list);

        // 获取所有的一级菜单，父菜单 id 为 0
        List<Menu> roots = new ArrayList<>();

        // 遍历
        for (Menu menu: list) {
            if (menu.getMenulevel() == 1) {
                roots.add(menu);
            }
        }

        // 删除一级菜单
        allList.removeAll(roots);

        // 对每一个一级菜单添加二级菜单
        for (Menu menu: roots) {
            // 设置子菜单
            menu.setChildMenu(getCurrentChildrenMenu(menu));
        }
        return roots;
    }

    /**
     * 通过父菜单获取子菜单列表
     * @param parentMenu 父菜单
     * @return 子菜单列表
     */
    private static List<Menu> getCurrentChildrenMenu(Menu parentMenu) {
        // 判断当前节点是否已经存在子结点
        List<Menu> childMenuList;
        if (parentMenu.getChildMenu() == null) {
            childMenuList = new ArrayList<>();;
        } else {
            childMenuList = parentMenu.getChildMenu();
        }

        // 遍历所有的菜单，除了一级菜单，之前删过
        for (Menu childMenu: allList) {
            if (parentMenu.getId() == childMenu.getMenuparentcode()) {
                // 某个菜单的父菜单 id 等于当前菜单，这个菜单就是子菜单
                childMenuList.add(childMenu);
            }
        }

        allList.removeAll(childMenuList);

        return childMenuList;
    }
}
