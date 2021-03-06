package com.zhiyou100.video.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.video.mapper.AdminMapper;
import com.zhiyou100.video.model.Admin;
import com.zhiyou100.video.model.AdminExample;
import com.zhiyou100.video.service.AdminService;
@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	AdminMapper am ;

	public void setAm(AdminMapper am) {
		this.am = am;
	}

	@Override
	public Admin login(String name, String password) {
		AdminExample  ae = new AdminExample();
		ae.createCriteria().andLoginNameEqualTo(name).andLoginPwdEqualTo(password);
		List<Admin> list = am.selectByExample(ae);
	     Admin admin = list.get(0);
		return admin;
	}
  
	
	
}
