package com.zhiyou100.video.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhiyou100.video.model.Course;
import com.zhiyou100.video.model.Speaker;
import com.zhiyou100.video.model.Subject;
import com.zhiyou100.video.model.Video;
import com.zhiyou100.video.service.UserCourseService;

@Controller
public class UserCourseController {
    @Autowired
	UserCourseService   ucs;
	
	@RequestMapping("/front/course/index.action")
    public  String   courseIndex(Integer subjectId,Model md,HttpSession session) {
		Subject  subject = ucs.findSubjectById(subjectId);
		
		md.addAttribute("subject", subject);
		
		List<Course>  co  =  ucs.findAllCourseAndVideo(subjectId);
	
		System.out.println(co);
		for (Course course : co) {
			List<Video> videoList = course.getVideoList();
			for (Video video : videoList) {
				Integer videoLength = video.getVideoLength();
				     int hh = videoLength/60/60;
				     int m = videoLength/60;
				     String mm=""+m;
				     if (m<10) {
						mm="0"+m;
					}
				     int s = videoLength%60;
				     String ss=""+s;
				     if (s<10) {
						ss="0"+s;
					}
				    System.out.println(hh+"----"+mm+"---"+ss);
				  video.setCName("0"+hh+":"+mm+":"+ss);
			}
		}
		
		md.addAttribute("courses", co);
		
		return "/front/course/index";
	}
    
	
	@RequestMapping("/front/video/index.action")
    public String courseVideo(Integer videoId ,Integer subjectId, Model md){
    	
    	Subject  subject = ucs.findSubjectById(subjectId);
		
		md.addAttribute("subject", subject);
		md.addAttribute("videoId", videoId);
    	return "/front/video/index";
    }
               
	
    @RequestMapping("/front/video/videoData.action")
	public String videoData(Integer videoId, Model md){
    	  Video  v = ucs.findVideoAllByid(videoId);
    	  md.addAttribute("video", v);
    	  Integer speakerId = v.getSpeakerId();
    	  Integer courseId = v.getCourseId();
    	  Course  co = ucs.findCourseById(courseId);
    	  Speaker sp = ucs.findSpeakerById(speakerId);
    	  md.addAttribute("course", co);
    	  md.addAttribute("speaker", sp);
    	  
    	  List<Video> list= ucs.findVideoAllandSpeakerNameById(speakerId);
    	   System.out.println(list);
    	 md.addAttribute("videoList", list);
    	  return "/front/video/content";
    }
	
    
    
}
