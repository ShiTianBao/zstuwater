package com.stb.zstuwater.unit;

import com.stb.zstuwater.persistent.entity.Menu;
import com.stb.zstuwater.persistent.entity.Order;
import com.stb.zstuwater.persistent.entity.User;
import com.stb.zstuwater.persistent.entity.to.MainMenu;
import com.stb.zstuwater.persistent.entity.to.ReturnOrder;
import com.stb.zstuwater.persistent.repository.UserRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class CommonUnit {


    /**
     * 将menuList改为mainMenuList
     * @param menuList
     * @return
     */
    public static List<MainMenu> getMainMenu(List<Menu> menuList) {
        List<MainMenu> mainMenuList = new ArrayList<>();
        for(Menu menu : menuList) {
            MainMenu mainMenu = new MainMenu();
            mainMenu.setId(menu.getId());
            mainMenu.setFatherId(menu.getFatherId());
            mainMenu.setIcon(menu.getIcon());
            mainMenu.setMenuName(menu.getMenuName());
            mainMenu.setUrl(menu.getUrl());
            mainMenu.setSortIndex(menu.getSortIndex());
            mainMenuList.add(mainMenu);
        }
        return mainMenuList;
    }

    /**
     * 得到要返回的orderlist
     * @param orderList
     * @param userRepository
     * @return
     */
    public static List<ReturnOrder> getReturnOrder(List<Order> orderList, UserRepository userRepository) {
        List<ReturnOrder> returnOrderList = new ArrayList<>();
        for(Order order : orderList) {
            ReturnOrder returnOrder = new ReturnOrder();
            Optional<User> userOptional = userRepository.findById(order.getUserId());
            User user = userOptional.get();
            returnOrder.setArea(user.getArea());
            returnOrder.setBuild(user.getBuild());
            returnOrder.setDormitory(user.getDormitory());
            returnOrder.setNickname(user.getNickname());
            returnOrder.setArriveTime(order.getArriveTime());
            returnOrder.setBookTime(order.getBookTime());
            returnOrder.setId(order.getId());
            returnOrder.setState(order.getState());
            returnOrder.setUserId(order.getUserId());
            returnOrder.setStaffName(order.getStaffName());
            returnOrderList.add(returnOrder);
        }
        return returnOrderList;
    }

    public static String getTime() {
        Date date = new Date();
        SimpleDateFormat temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //这是24时
        String dateStr = temp.format(date);
        return dateStr;
    }
}
