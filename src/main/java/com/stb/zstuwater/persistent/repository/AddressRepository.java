package com.stb.zstuwater.persistent.repository;


import com.stb.zstuwater.persistent.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    //根据area和build找出楼名
    @Transactional
    @Modifying
    @Query(value = "select * from address att where att.area = :area and att.build = :build", nativeQuery = true)
    public List<Address> findByAreaAndBuild(@Param("area")String area, @Param("build")String build);

    //根据area和build找出楼名
    @Transactional
    @Modifying
    @Query(value = "select * from address att where att.name = :addressName", nativeQuery = true)
    public List<Address> findByName(@Param("addressName")String addressName);

}
