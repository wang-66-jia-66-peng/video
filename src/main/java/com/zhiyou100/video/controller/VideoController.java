package com.zhiyou100.video.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiyou100.video.model.Course;
import com.zhiyou100.video.model.PackageVO;
import com.zhiyou100.video.model.Page;
import com.zhiyou100.video.model.Speaker;
import com.zhiyou100.video.model.Video;
import com.zhiyou100.video.service.VideoService;

@Controller
public class VideoController {
     @Autowired
	 VideoService vs ;
     
     @RequestMapping("/video/videoList.action")
     public String  videoList(@RequestParam(defaultValue="")String videoKeyWord,@RequestParam(defaultValue="")Integer  videoSearchSpeaker ,
    		 @RequestParam(defaultValue="")Integer videoSearchCourse,
    		 @RequestParam(value="page",required=false,defaultValue="1")Integer currentPage,HttpServletResponse res,HttpServletRequest req, Model md) {
    	 
    	 if (videoKeyWord==null || videoSearchSpeaker ==null || videoSearchCourse==null) {
    		 videoKeyWord="";
    		 videoSearchSpeaker=null;
    		 videoSearchCourse=null;
 		 }
     	 
         md.addAttribute("roleKeyword", videoKeyWord);
  	     md.addAttribute("videoSearchSpeaker", videoSearchSpeaker);
  	     md.addAttribute("videoSearchCourse", videoSearchCourse);
    	 
  	     currentPage = req.getParameter("page")==null ? 1:Integer.parseInt(req.getParameter("page"));
    	
  	    
  	      Page<PackageVO>  pp = vs.findVideoBySearch(videoKeyWord,videoSearchSpeaker,videoSearchCourse,currentPage);
    	 
    	  System.out.println(pp);
    	  
    	   List<Speaker>  sp =  vs.findSpeakerAll();
    	   
    	   List<Course>  cr = vs.findCourseAll();
    	   
    	   md.addAttribute("speaker", sp);
    	   md.addAttribute("course", cr);
    	   md.addAttribute("page", pp);
    	 
    	 
		  return  "/video/videoList";
	}
     

    
     @RequestMapping("/video/deleteVideo.action")
     public String deleteVideo(Integer id) {
		   vs.deleteVideoById(id);
    	 
    	 return "forward:/video/videoList.action";
	}
     
     @RequestMapping("/video/addVideo01.action")
    public String  addVideo01(Model md) {
    	  
      List<Speaker>  sp =  vs.findSpeakerAll();
  	   
  	  List<Course>  cr = vs.findCourseAll();
  	   
  	   md.addAttribute("speaker", sp);
  	   md.addAttribute("course", cr);
    	 
		return "/video/addVideo";
	}
     @RequestMapping("/video/addVideo02.action")
     public String  addVideo02(Video v) {
    	
    		 vs.addVideo(v);
		
    	 return "forward:/video/videoList.action";
     }
    
     
     
     @RequestMapping("/video/updateVideo.action")
     public String updateVideo(Integer id, Model md) {
    	 Video vd =  vs.findVideoById(id);
    	   
    	 List<Speaker>  sp =  vs.findSpeakerAll();
    	   
     	 List<Course>  cr = vs.findCourseAll();
     	   md.addAttribute("video", vd);
     	   md.addAttribute("speaker", sp);
     	   md.addAttribute("course", cr);
		   return "/video/updateVideo";
	 }
  
     @RequestMapping("/video/updateVideo02.action")
      public String updateVideo(Video v,Integer id) {
    	  System.out.println(v);
    	   System.out.println(id);  
    	    vs.updateVideo(v);
    	  
		return "forward:/video/videoList.action";
	}
     
     @RequestMapping("/video/deteleVideoLarge.action")
     public String  deteleVideoLarge( Integer[] id) {
    	  for (Integer integer : id) {
    		  if (integer!=null) {
				vs.deleteVideoById(integer);
			}
		}
    	 
    	 return "forward:/video/videoList.action";
	}
     

     
     
     
     

} 

