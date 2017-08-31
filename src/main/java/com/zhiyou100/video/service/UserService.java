package com.zhiyou100.video.service;

import java.util.List;

import com.zhiyou100.video.model.User;

public interface UserService {

	List<User> userLogin(User u);

	void userRegist(User u);

	void userProfile(User u);

	User findUserById(Integer id);

	void updateAvatar(User u);

	List<User> findUserByEmail(String email);

	List<User> findUserByUser(User u);

}
