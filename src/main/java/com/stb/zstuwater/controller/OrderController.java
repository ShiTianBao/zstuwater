package com.stb.zstuwater.controller;

import com.stb.zstuwater.common.CommonMsg;
import com.stb.zstuwater.common.GlobalConstant;
import com.stb.zstuwater.persistent.entity.Order;
import com.stb.zstuwater.persistent.repository.OrderRepository;
import com.stb.zstuwater.persistent.repository.UserRepository;
import com.stb.zstuwater.unit.CommonUnit;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController implements GlobalConstant {

    @Resource
    OrderRepository orderRepository;

    @Resource
    UserRepository userRepository;

    @GetMapping("/list")
    public CommonMsg list() {
        List<Order> orderList = orderRepository.findAll();
        return new CommonMsg(CommonUnit.getReturnOrder(orderList, userRepository));
    }

    @GetMapping("/pending")
    public CommonMsg pending() {
        List<Order> orderList = orderRepository.findOrderByState(PENDING);
        return new CommonMsg(CommonUnit.getReturnOrder(orderList, userRepository));
    }

    @GetMapping("/being")
    public CommonMsg being() {
        List<Order> orderList = orderRepository.findOrderByState(BEING);
        return new CommonMsg(CommonUnit.getReturnOrder(orderList, userRepository));
    }

    @GetMapping("/finished")
    public CommonMsg finished() {
        List<Order> orderList = orderRepository.findOrderByStateAndDate(FINISHED, CommonUnit.getTime().substring(0,10)+"%");
        System.out.println(CommonUnit.getTime().substring(0,10));
        return new CommonMsg(CommonUnit.getReturnOrder(orderList, userRepository));
    }

    @PostMapping("/delivery")
    public CommonMsg delivery(@RequestParam(value = "delivery[]") String[] delivery, @RequestParam(value = "staffName") String staffName) {
        System.out.println(staffName);
        System.out.println(delivery.length);
        for(int i=0; i<delivery.length; i++) {
            orderRepository.updateOrderByIdAndStaffName(BEING, Integer.parseInt(delivery[i]), staffName);
        }
        return new CommonMsg();
    }

}
