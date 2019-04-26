package com.stb.zstuwater.controller;

import com.stb.zstuwater.common.CommonMsg;
import com.stb.zstuwater.common.GlobalConstant;
import com.stb.zstuwater.persistent.entity.Address;
import com.stb.zstuwater.persistent.entity.Order;
import com.stb.zstuwater.persistent.entity.Relation;
import com.stb.zstuwater.persistent.entity.User;
import com.stb.zstuwater.persistent.entity.to.ReturnOrder;
import com.stb.zstuwater.persistent.repository.AddressRepository;
import com.stb.zstuwater.persistent.repository.OrderRepository;
import com.stb.zstuwater.persistent.repository.RelationRepository;
import com.stb.zstuwater.persistent.repository.UserRepository;
import com.stb.zstuwater.unit.CommonUnit;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/delivery")
public class DeliveryController implements GlobalConstant {

    @Resource
    OrderRepository orderRepository;

    @Resource
    AddressRepository addressRepository;

    @Resource
    UserRepository userRepository;

    @Resource
    RelationRepository relationRepository;


    @GetMapping("/list")
    public CommonMsg getList(String n) {
        int nInt = Integer.parseInt(n);
        if(getBefore() != null) {
            String name = getBefore();
            List<Order> orderList = getOrderByName(name,nInt);
            List<ReturnOrder> returnOrderList = CommonUnit.getReturnOrder(orderList, userRepository);
            return new CommonMsg(returnOrderList);
        }else {
            List<Order> orderList = getFullList(nInt);
            if(orderList!=null) {
                List<ReturnOrder> returnOrderList = CommonUnit.getReturnOrder(orderList, userRepository);
                return new CommonMsg(returnOrderList);
            }else{
                String nowName = getAddressName();
                List<Order> orderList1 = getOrderByName(nowName,nInt);
                List<ReturnOrder> returnOrderList = CommonUnit.getReturnOrder(orderList1,userRepository);
                return new CommonMsg(returnOrderList);
            }

        }
    }

    /**
     * 如果某个楼的桶数大于n桶，则先送此楼
     * @param n
     * @return
     */
    private List<Order> getFullList(int n) {
        String nowStr = CommonUnit.getTime();
        List<Order> orderList = orderRepository.findOrderByState(PENDING);
        List<Address> addressList = addressRepository.findAll();
        Map<String, Integer> map1 = new HashMap<>();
        for(Address address : addressList) {
            map1.put(address.getName(),0);
        }
        for(Order order : orderList) {
            User user = userRepository.getOne(order.getUserId());
            List<Address> addressList1 = addressRepository.findByAreaAndBuild(user.getArea(),user.getBuild());
            Address address = addressList1.get(0); //这个订单所在楼
            for(Map.Entry<String, Integer> entry : map1.entrySet()) {
                if(entry.getKey().equals(address.getName())) {
                    map1.put(entry.getKey(), entry.getValue()+1);
                }
            }
        }
        String nowName = null;
        for(Map.Entry<String, Integer> entry : map1.entrySet()) {
            if(entry.getValue() >= n) {
                nowName = entry.getKey();
                break;
            }
        }
        if(nowName != null) { //找到大于n的楼
            List<Order> returnOrder = new ArrayList<>();
            List<Address> addressList1 = addressRepository.findByName(nowName);
            returnOrder = getOrderByAddressList(addressList,n,1);
            if(returnOrder!=null) return returnOrder;
        }
        return null;
    }

    /**
     * 找到最应该送水的那栋楼
     * @return
     */
    private String getAddressName() {
        String nowTime = CommonUnit.getTime();
        List<Order> orderList = orderRepository.findOrderByState(PENDING);
        List<Address> addressList = addressRepository.findAll();
        Map<String, Integer> map1 = new HashMap<>();
        for(Address address : addressList) {
            map1.put(address.getName(),0);
        }
        for(Order order : orderList) {
            User user = userRepository.getOne(order.getUserId());
            List<Address> addressList1 = addressRepository.findByAreaAndBuild(user.getArea(),user.getBuild());
            Address address = addressList1.get(0); //这个订单所在楼
            for(Map.Entry<String, Integer> entry : map1.entrySet()) {
                if(entry.getKey().equals(address.getName())) {
                    int grade = 0;
                    String nowDay = nowTime.substring(9,10);
                    String orderDay = order.getBookTime().substring(9,10);
                    if(nowDay.equals(orderDay)) {
                        grade = 1;
                    }else{
                        if((Integer.parseInt(nowDay)-Integer.parseInt(orderDay)) == 1) {
                            grade = 2;
                        }else {
                            grade = 4;
                        }
                    }
                    map1.put(entry.getKey(), entry.getValue()+grade);
                }
            }
        }
        String nowName = null;
        int maxGrade = 0;
        for(Map.Entry<String, Integer> entry : map1.entrySet()) {
            if(entry.getValue()>maxGrade) {
                maxGrade = entry.getValue();
                nowName = entry.getKey();
            }
        }
        return nowName;
    }

    /**
     * 根据楼名获取orderlist
     * @param nowName
     * @param n
     * @return
     */
    private List<Order> getOrderByName(String nowName, int n) {
        List<Order> returnOrder = new ArrayList<>();
        Relation r = new Relation();
        r.setAddressName(nowName);
        Optional<Relation> relationOptional = relationRepository.findOne(Example.of(r));
        r = relationOptional.get();
        String near1 = r.getNear1();
        String near2 = r.getNear2();
        List<String> nameList = new ArrayList<>();
        nameList.add(nowName);
        nameList.add(near1);
        nameList.add(near2);
        for(String name : nameList) {
            List<Address> addressList = addressRepository.findByName(name);
            returnOrder = getOrderByAddressList(addressList,n,2);
        }
        return returnOrder;
    }

    /**
     * 获取前天的那个水的楼
     * @return
     */
    private String getBefore(){
        String nowStr = CommonUnit.getTime();
        List<Order> orderList = orderRepository.findOrderByState(PENDING);
        for(Order order : orderList) {
            if((Integer.parseInt(order.getBookTime().substring(9,10))-Integer.parseInt(nowStr.substring(9,10)))>2) {
                User user = userRepository.getOne(order.getUserId());
                List<Address> addressList = addressRepository.findByAreaAndBuild(user.getArea(),user.getBuild());
                Address address = addressList.get(0);
                return address.getName();
            }
        }
        return null;
    }

    /**
     * 根据地址得出要返回的orderlist
     * @param addressList
     * @param n
     * @return
     */
    private List<Order> getOrderByAddressList(List<Address> addressList, int n, int flag) {
        List<Order> returnOrder = new ArrayList<>();
        for(Address address : addressList) {
            List<User> userList = userRepository.findByAreaAndBuild(address.getArea(),address.getBuild());
            for(User user : userList) {
                List<Order> orderList = orderRepository.findOrderByUserIdAndState(user.getId(),PENDING);
                for(Order order : orderList) {
                    returnOrder.add(order);
                    if(returnOrder.size()>=n) {
                        return returnOrder;
                    }
                }
            }
        }
        if(flag == 1) {
            return null;
        }else if(flag == 2){
            return returnOrder;
        }

        return null;

    }





}
