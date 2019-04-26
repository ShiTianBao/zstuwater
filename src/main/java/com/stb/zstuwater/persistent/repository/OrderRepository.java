package com.stb.zstuwater.persistent.repository;

import com.stb.zstuwater.persistent.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    //根据状态选出order
    @Transactional
    @Modifying
    @Query(value = "select * from order_table ott where ott.state = :state", nativeQuery = true)
    public List<Order> findOrderByState(@Param("state")String state);

    //根据userId选出table
    @Transactional
    @Modifying
    @Query(value = "select * from order_table ott where ott.user_id = :userId", nativeQuery = true)
    public List<Order> findOrderByUserId(@Param("userId")Integer userId);

    //根据userId选出table
    @Transactional
    @Modifying
    @Query(value = "select * from order_table ott where ott.user_id = :userId and ott.state = :state", nativeQuery = true)
    public List<Order> findOrderByUserIdAndState(@Param("userId")Integer userId, @Param("state") String state);

    //根据状态和送达时间选出order
    @Transactional
    @Modifying
    @Query(value = "select * from order_table ott where ott.state = :state and ott.arrive_time like :arriveTime", nativeQuery = true)
    public List<Order> findOrderByStateAndDate(@Param("state")String state, @Param("arriveTime")String arriveTime);

    //更新订单状态为配送中
    @Transactional
    @Modifying
    @Query(value = "update order_table ott set ott.state = :state , ott.staff_name = :staffName where ott.id = :id", nativeQuery = true)
    public void updateOrderByIdAndStaffName(@Param("state")String state, @Param("id")int id, @Param("staffName")String staffName);
}
