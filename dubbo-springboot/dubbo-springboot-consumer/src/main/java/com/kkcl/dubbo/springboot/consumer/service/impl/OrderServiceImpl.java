package com.kkcl.dubbo.springboot.consumer.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.kkcl.dubbo.springboot.api.bean.UserAddress;
import com.kkcl.dubbo.springboot.api.service.OrderService;
import com.kkcl.dubbo.springboot.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    //@Autowired
    @Reference // dubbo远程调用自动发现
    private UserService userService;

    @Override
    public List<UserAddress> initOrder(String userId) {
        System.out.println("用户Id:"+userId);
        List<UserAddress> addressList = userService.getUserAddressList(userId);
        return addressList;
    }
}
