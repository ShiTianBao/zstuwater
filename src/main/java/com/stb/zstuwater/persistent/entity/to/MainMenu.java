package com.stb.zstuwater.persistent.entity.to;

import com.stb.zstuwater.persistent.entity.Menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 在数据库菜单bean基础上添加主菜单（添加children来显示子菜单）
 */
public class MainMenu extends Menu implements Serializable {

    private List<Menu> children = new ArrayList<>(10);

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

}
