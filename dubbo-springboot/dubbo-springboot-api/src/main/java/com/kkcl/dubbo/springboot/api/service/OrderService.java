package com.kkcl.dubbo.springboot.api.service;

import com.kkcl.dubbo.springboot.api.bean.UserAddress;

import java.util.List;


public interface OrderService {
	public List<UserAddress> initOrder(String userId);
}
