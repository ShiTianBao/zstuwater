package com.stb.zstuwater.controller;

import com.stb.zstuwater.common.CommonMsg;
import com.stb.zstuwater.persistent.entity.Relation;
import com.stb.zstuwater.persistent.repository.RelationRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/relation")
public class RelationController {

    @Resource
    RelationRepository relationRepository;

    @GetMapping("/list")
    public CommonMsg getList() {
        return new CommonMsg(relationRepository.findAll());
    }

    @PostMapping("/add")
    public CommonMsg add(Relation relation) {
        relationRepository.saveAndFlush(relation);
        return new CommonMsg();
    }

}
