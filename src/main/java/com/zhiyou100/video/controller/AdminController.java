package com.zhiyou100.video.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhiyou100.video.model.Admin;
import com.zhiyou100.video.service.AdminService;

@Controller
public class AdminController {
	
	
     @Autowired
	 AdminService as;
	
	@RequestMapping("/login.action")
	 public String login(Admin ads,Model md,HttpServletRequest req,HttpServletResponse res) throws Exception {
		 System.out.println(ads);
		 
		 Admin ad = as.login(ads.getLoginName(),ads.getLoginPwd());
		  if (ad==null) {
			  req.getRequestDispatcher("/index.jsp").forward(req, res);  
		}
		 System.out.println(ad);
		 
		 req.getSession().setAttribute("ADMIN", ad);
		 
		 return "redirect:/video/videoList.action";
	} 
	
	
}
