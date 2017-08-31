package com.zhiyou100.video.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiyou100.video.model.Course;
import com.zhiyou100.video.model.Page;
import com.zhiyou100.video.model.Subject;
import com.zhiyou100.video.service.CourseService;

@Controller
public class CourseController {
    @Autowired
	CourseService  cs;
	
    @RequestMapping("/course/courseList.action")
	 public String courseList(Model md,@RequestParam(value="page",required=false,defaultValue="1")Integer page ) {
		 //System.out.println("1111111111");
    	 Page<Course> pc = cs.findAllCourse(page);
		  md.addAttribute("page", pc);
		 
		  return "/course/courseList";
	}
   
    @RequestMapping("/course/addCourse01.action")
    public String addCourse01(Model md) {
    	List<Subject> list =  cs.findAllSubject();
    	
    	md.addAttribute("subject", list);
		
    	return "/course/addCourse";
	}
    
    
    @RequestMapping("/course/addCourse02.action")
    public String addCourse02(Course cu) {
    	
    	     cs.addCourse(cu);
    	
		
    	return "redirect:/course/courseList.action";
	}
    
    
    @RequestMapping("/course/updateCourse01.action")
    public String updateCourse01(Integer id,Model md) {
    	System.out.println("id:----"+id);
         List<Subject> list =  cs.findAllSubject();
    	 md.addAttribute("subject", list);
    	 Course  course =  cs.findCourseById(id);
    	 md.addAttribute("course", course);
		return "/course/updateCourse";
	}
    
    
    
    @RequestMapping("/course/updateCourse02.action")
    public String updateCourse02(Course co) {
    	   System.out.println("222222222"+co);
    	    cs.updateCourse(co);
		return "redirect:/course/courseList.action";
	}
    
    @RequestMapping("/course/deleteCourse.action")
    public String deleteCourse(Integer id) {
		   cs.deleteCourse(id);
    	
    	return "redirect:/course/courseList.action";
	}
    
    
}
