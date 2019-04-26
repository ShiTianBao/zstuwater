package com.stb.zstuwater.persistent.entity.to;

import com.stb.zstuwater.persistent.entity.Order;

public class ReturnOrder extends Order {

    private String nickname;
    private String area;
    private String build;
    private String dormitory;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getDormitory() {
        return dormitory;
    }

    public void setDormitory(String dormitory) {
        this.dormitory = dormitory;
    }

    @Override
    public String toString() {
        return "ReturnOrder{" +
                "nickname='" + nickname + '\'' +
                ", area='" + area + '\'' +
                ", build='" + build + '\'' +
                ", dormitory='" + dormitory + '\'' +
                '}';
    }
}
