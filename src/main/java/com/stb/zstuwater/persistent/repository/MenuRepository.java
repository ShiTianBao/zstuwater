package com.stb.zstuwater.persistent.repository;

import com.stb.zstuwater.persistent.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;


public interface MenuRepository extends JpaRepository<Menu,Integer> {
    //查找所有的父菜单
    @Transactional
    @Modifying
    @Query(value = "select * from menu m where m.father_id = 0", nativeQuery = true)
    public List<Menu> findAllMainMenu();

    //根据父菜单id找出所有子菜单
    @Transactional
    @Modifying
    @Query(value = "select * from menu m where m.father_id = :fatherId", nativeQuery = true)
    public List<Menu> findAllChildrenMenu(@Param("fatherId")int fatherId);
}
