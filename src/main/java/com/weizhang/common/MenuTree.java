package com.weizhang.common;

import com.weizhang.model.Menu;

import java.util.ArrayList;
import java.util.List;

public class MenuTree {
    private List<Menu> menuList = new ArrayList<Menu>();

    public MenuTree(List<Menu> menuList) {
        this.menuList = menuList;
    }

    //建立树形结构
    public List<Menu> builTree() {
        List<Menu> treeMenus = new ArrayList<Menu>();
        for (Menu menuNode : getRootNode()) {
            menuNode = buildChilTree(menuNode);
            treeMenus.add(menuNode);
        }
        return treeMenus;
    }

    //递归，建立子树形结构
    private Menu buildChilTree(Menu pNode) {
        List<Menu> chilMenus = new ArrayList<Menu>();
        for (Menu menuNode : menuList) {
            if (menuNode.getParentId() == pNode.getId()) {
                chilMenus.add(buildChilTree(menuNode));
            }
        }
        pNode.setChildren(chilMenus);
        return pNode;
    }

    //获取根节点
    private List<Menu> getRootNode() {
        List<Menu> rootMenuLists = new ArrayList<Menu>();
        for (Menu menuNode : menuList) {
            if (menuNode.getParentId() == 0) {
                rootMenuLists.add(menuNode);
            }
        }

        return rootMenuLists;
    }
}
