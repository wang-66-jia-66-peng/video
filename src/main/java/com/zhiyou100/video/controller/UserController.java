package com.zhiyou100.video.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhiyou100.video.model.User;
import com.zhiyou100.video.service.UserService;
import com.zhiyou100.video.util.MD5Utils;
import com.zhiyou100.video.util.MailUtil;

@Controller
public class UserController {
    @Autowired
	UserService us;
	
    
    
    @RequestMapping(value="/front/user/login.action")
    @ResponseBody
    public User userLogin(User u,Model md,HttpSession session) {
    	
        List<User> list =  us.userLogin(u);
        User user = new User();
       
        if(list.isEmpty()==true){
        	  user.setId(0);
        }else{
    	      user=list.get(0);
        }
        session.setAttribute("_front_user", user);
        return user;
    }
  
    @RequestMapping(value="/front/user/regist.action")
    @ResponseBody
    public String  userRegist(User u) {
    	System.out.println(u);
    	
    	us.userRegist(u);
    	
		return "success";
	}
    
    @RequestMapping("/front/user/userIndex.action")
    public String userPersonal(HttpSession session,Model md){
    	User att =(User) session.getAttribute("_front_user");
    	Integer id = att.getId();
    	User use  =  us.findUserById(id);
    	session.setAttribute("_front_user", use );
    	md.addAttribute("user", use);
    	//session.setAttribute("user",att);
    	return  "/front/user/index";
    }
    @RequestMapping("/front/user/profile.action")
    public String userProfile(HttpSession session,Model md) {
     User att =(User) session.getAttribute("_front_user"); 
    	Integer id = att.getId();
    	User use  =  us.findUserById(id);
    	session.setAttribute("_front_user", use );
    	md.addAttribute("user", use);
		return "/front/user/profile";
	}
    
    @RequestMapping(value="/front/user/profile02.action",method=RequestMethod.POST)
    public String userProfile02(User u,String birthdayStr) throws Exception {
    	SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
    	Date parse = sim.parse(birthdayStr);
        u.setBirthday(parse);
    	 us.userProfile(u);
    	
    	return "redirect:/front/user/userIndex.action";
    }
    
    
    @RequestMapping(value="/front/user/avatar.action")
    public String updateAvatar(HttpSession session,Model md) {
    	User att =(User) session.getAttribute("_front_user");
    	Integer id = att.getId();
    	User use  =  us.findUserById(id);
    	session.setAttribute("_front_user", use );
    	md.addAttribute("user", use);
		return "/front/user/avatar";
	}
    
    @RequestMapping(value="/front/user/avatar02.action")
    public String updateAvatar02(User u,Model md,MultipartFile pic,HttpServletRequest req) throws Exception {
    	  
    	
        String k = UUID.randomUUID().toString().replaceAll("-", "");
    	
	    String ext = FilenameUtils.getExtension(pic.getOriginalFilename());
	     
	    String fileName=k+"."+ext;
	    
	    u.setHeadUrl(fileName);
	    
        String path="D:\\upload";
        
        pic.transferTo(new File(path+"\\"+fileName));
    	
         us.updateAvatar(u);
        
    	return "redirect:/front/user/userIndex.action";
    }
    
    
    @RequestMapping("/front/user/password.action")
    public String userPassword(HttpSession session,Model md){
    	User att =(User) session.getAttribute("_front_user");
    	Integer id = att.getId();
    	User use  =  us.findUserById(id);
    	md.addAttribute("user", use);
    	return "/front/user/password";
    }
    
    @RequestMapping("/front/user/oldPassword.action")
    @ResponseBody
	public String oldPassword(String oldPassword,String newPassword,HttpSession session){
    	String str = "fail";
    	User att =(User) session.getAttribute("_front_user");
    	Integer id = att.getId();
    	User use =  us.findUserById(id);
    	String md5 = MD5Utils.getMD5(oldPassword.trim());
    	String md52 = MD5Utils.getMD5(md5.trim());
    	System.out.println(md5+"-----"+md52+"------"+newPassword+"----"+use.getPassword());
    	if (md52.equals(use.getPassword())) {
			use.setPassword(newPassword);
			us.userProfile(use);
			str="success";
		}
    	
    	return str;
    }
    
    @RequestMapping("/front/user/forget_pwd.action")
    public String forgetPwd(){
    	return "/front/user/forget_pwd";
    }
    
    @RequestMapping("/front/user/sendemail.action")
    @ResponseBody
    public String sendemail(String email) throws Exception{
    	 System.out.println(email);
    	 String str = "success";
    	 Random ran = new Random();
    	 int i = ran.nextInt(99999-10000+1)+10000;
    	 List<User> lu = us.findUserByEmail(email);
    	if (lu.isEmpty()==true) {
			str="fail";
		}else{
    	 lu.get(0).setCaptcha(""+i);
    	 us.updateAvatar(lu.get(0));
    	 MailUtil.send(email, "验证码", "您的验证码:"+i+
    	"<a href='http://192.168.6.175:8080/video/front/user/forget_pwd.action'>填写验证码</a>");
		}
    	return str;
    }
    
    
    @RequestMapping("/front/user/forget_pwd02.action")
    public String forgetPwd02(User u, Model md){
       
    	 List<User>  list = us.findUserByUser(u);
    	if (list.isEmpty()==true) {
			return "redirect:/front/user/forget_pwd.action";
		}
		md.addAttribute("user", list.get(0));
		
    	return "/front/user/reset_pwd";
    }
    
    @RequestMapping(value="/front/user/resetpwd.action" ,method=RequestMethod.POST)
    public String resetPwd(User u,HttpSession session,Model md) throws Exception{
    	us.updateAvatar(u);
    	User user = us.findUserById(u.getId());
    	session.setAttribute("_front_user", user);
    	md.addAttribute("user", user);
    	return "/front/user/index";
    }
    
    
    @RequestMapping("/front/user/logout.action")
    public String logout(HttpSession session){
    	session.removeAttribute("_front_user");
    	
    	return "redirect:/front/index.jsp";
    }
    
    @RequestMapping("/front/user/souye.action")
    public String souye(){
    	return "redirect:/front/index.jsp";
    }
    
    
    
    
    
}
