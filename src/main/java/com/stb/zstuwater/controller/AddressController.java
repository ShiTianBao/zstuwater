package com.stb.zstuwater.controller;

import com.stb.zstuwater.common.CommonMsg;
import com.stb.zstuwater.persistent.entity.Address;
import com.stb.zstuwater.persistent.repository.AddressRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Resource
    AddressRepository addressRepository;


    @GetMapping("/list")
    public CommonMsg getList() {
        return new CommonMsg(addressRepository.findAll());
    }

    @PostMapping("/add")
    public CommonMsg add(Address address) {
        addressRepository.saveAndFlush(address);
        return new CommonMsg();
    }


}
