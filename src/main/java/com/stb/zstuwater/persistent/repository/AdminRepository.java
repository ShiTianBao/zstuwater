package com.stb.zstuwater.persistent.repository;

import com.stb.zstuwater.persistent.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
}
