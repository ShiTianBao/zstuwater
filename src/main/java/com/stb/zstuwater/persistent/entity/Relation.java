package com.stb.zstuwater.persistent.entity;

import javax.persistence.*;

@Entity
@Table(name = "relation")
public class Relation {

    private Integer id;
    private String area;
    private String addressName;
    private String near1;
    private String near2;

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
    @Column(name = "area")
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Basic
    @Column(name = "address_name")
    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    @Basic
    @Column(name = "near_1")
    public String getNear1() {
        return near1;
    }

    public void setNear1(String near1) {
        this.near1 = near1;
    }

    @Basic
    @Column(name = "near_2")
    public String getNear2() {
        return near2;
    }

    public void setNear2(String near2) {
        this.near2 = near2;
    }

    @Override
    public String toString() {
        return "Relation{" +
                "id=" + id +
                ", area='" + area + '\'' +
                ", addressName='" + addressName + '\'' +
                ", near1='" + near1 + '\'' +
                ", near2='" + near2 + '\'' +
                '}';
    }
}
