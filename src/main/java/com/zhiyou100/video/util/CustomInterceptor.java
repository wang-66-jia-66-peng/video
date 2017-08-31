package com.zhiyou100.video.util;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zhiyou100.video.model.User;


public class CustomInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
          // System.out.println("视图渲染显示后调用,可以有日志,清理一些资源");
	}

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object arg2, ModelAndView mv)
			throws Exception {
         //  System.out.println("controller执行后调用,参数为req,res,controller对象和数据视图,可以对数据进行再加工");
	}
   
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object obj) throws Exception {
		System.out.println("controller执行前调用,参数为req , res , controller的对象.只有返回true才能继续,用来做用户登录验证与权限校验");
		   User us =(User) req.getSession().getAttribute("_front_user");
		   boolean v= true;
		   if (us.getEmail()==null) {
			  //res.sendRedirect(req.getContextPath()+"/front/index.jsp"); 
			  v=false;
		   }  
		   return v;
	}

}
