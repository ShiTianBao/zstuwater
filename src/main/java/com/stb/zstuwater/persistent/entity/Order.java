package com.stb.zstuwater.persistent.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "order_table")
public class Order {

    private Integer id;
    private Integer userId;
    private String bookTime;
    private String arriveTime;
    private String staffName;
    private String state;

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
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "book_time")
    public String getBookTime() {
        return bookTime;
    }

    public void setBookTime(String bookTime) {
        this.bookTime = bookTime;
    }

    @Basic
    @Column(name = "arrive_time")
    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    @Basic
    @Column(name = "staff_name")
    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    @Basic
    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", bookTime='" + bookTime + '\'' +
                ", arriveTime='" + arriveTime + '\'' +
                ", staffName='" + staffName + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
