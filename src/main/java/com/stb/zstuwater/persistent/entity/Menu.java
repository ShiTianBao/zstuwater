package com.stb.zstuwater.persistent.entity;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "menu")
public class Menu {
    private Integer id;
    private int fatherId;
    private Integer sortIndex;
    private String icon;
    private String menuName;
    private String url;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "father_id")
    public int getFatherId() {
        return fatherId;
    }

    public void setFatherId(int fatherId) {
        this.fatherId = fatherId;
    }

    @Basic
    @Column(name = "sort_index")
    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    @Basic
    @Column(name = "icon")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Basic
    @Column(name = "menu_name")
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return id == menu.id &&
                fatherId == menu.fatherId &&
                Objects.equals(sortIndex, menu.sortIndex) &&
                Objects.equals(icon, menu.icon) &&
                Objects.equals(menuName, menu.menuName) &&
                Objects.equals(url, menu.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fatherId, sortIndex, icon, menuName, url);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", fatherId=" + fatherId +
                ", sortIndex=" + sortIndex +
                ", icon='" + icon + '\'' +
                ", menuName='" + menuName + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
