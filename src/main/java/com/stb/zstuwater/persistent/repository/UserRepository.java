package com.stb.zstuwater.persistent.repository;

import com.stb.zstuwater.persistent.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    //根据area和build找出用户
    @Transactional
    @Modifying
    @Query(value = "select * from user_table utt where utt.area = :area and utt.build = :build", nativeQuery = true)
    public List<User> findByAreaAndBuild(@Param("area")String area,@Param("build")String build);
}
