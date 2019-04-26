package com.stb.zstuwater.controller;

import com.stb.zstuwater.common.CommonMsg;
import com.stb.zstuwater.common.GlobalConstant;
import com.stb.zstuwater.persistent.entity.Staff;
import com.stb.zstuwater.persistent.repository.StaffRepository;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController implements GlobalConstant {

    @Resource
    StaffRepository staffRepository;

    @GetMapping("/list")
    public CommonMsg getList() {
        CommonMsg cm = new CommonMsg();
        cm.setData(staffRepository.findAll());
        return cm;
    }

    @PostMapping("/add")
    public CommonMsg add(Staff staff) {
        System.out.println(staff.toString());
        staffRepository.saveAndFlush(staff);
        return new CommonMsg();
    }

    @GetMapping("/delivery")
    public CommonMsg delivery() {
        List<Staff> staffList = staffRepository.FindStaffByState(STAFF_STATE_FREE);
        return new CommonMsg(staffList);
    }


    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {

        //转换日期
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
    }
}
