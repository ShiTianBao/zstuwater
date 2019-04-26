package com.stb.zstuwater.controller;

import com.stb.zstuwater.common.CommonMsg;
import com.stb.zstuwater.persistent.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserRepository userRepository;

    @RequestMapping("/list")
    public CommonMsg getList() {
        return new CommonMsg(userRepository.findAll());
    }
}
