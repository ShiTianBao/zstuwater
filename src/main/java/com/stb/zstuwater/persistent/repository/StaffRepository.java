package com.stb.zstuwater.persistent.repository;

import com.stb.zstuwater.persistent.entity.Order;
import com.stb.zstuwater.persistent.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Integer> {

    //选出空闲的配送员
    @Transactional
    @Modifying
    @Query(value = "select * from staff sss where sss.state = :state", nativeQuery = true)
    public List<Staff> FindStaffByState(@Param("state")String state);

}
