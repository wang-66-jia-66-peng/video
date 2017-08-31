package com.zhiyou100.video.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.video.mapper.UserMapper;
import com.zhiyou100.video.model.User;
import com.zhiyou100.video.model.UserExample;
import com.zhiyou100.video.service.UserService;
@Service
public class UserServiceImpl implements UserService {
      @Autowired
	  UserMapper  um;

	public void setUm(UserMapper um) {
		this.um = um;
	}

	@Override
	public List<User> userLogin(User u) {
		UserExample ue = new UserExample();
		ue.createCriteria().andEmailEqualTo(u.getEmail()).andPasswordEqualTo(u.getPassword());
		List<User> list = um.selectByExample(ue);
		return list;
	}

	@Override
	public void userRegist(User u) {
		
		 um.insertSelective(u);
	}

	@Override
	public void userProfile(User u) {
		um.updateByPrimaryKeySelective(u);
	}

	@Override
	public User findUserById(Integer id) {
		
		return um.selectByPrimaryKey(id);
	}

	@Override
	public void updateAvatar(User u) {
		um.updateByPrimaryKeySelective(u);
	}

	@Override
	public List<User> findUserByEmail(String email) {
		UserExample  ue = new UserExample();
		ue.createCriteria().andEmailEqualTo(email);
	    List<User> list = um.selectByExample(ue);
		return list;
	}

	@Override
	public List<User> findUserByUser(User u) {
		UserExample  ue = new UserExample();
		ue.createCriteria().andEmailEqualTo(u.getEmail()).andCaptchaEqualTo(u.getCaptcha());
	    List<User> list = um.selectByExample(ue);
	     return list;
	}
	
	
	
	
	
}
