package com.kkcl.gmall.service;

import java.util.List;

import com.kkcl.gmall.bean.UserAddress;

public interface OrderService {
	public List<UserAddress> initOrder(String userId);
}
