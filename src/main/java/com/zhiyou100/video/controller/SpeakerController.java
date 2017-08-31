package com.zhiyou100.video.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiyou100.video.model.Page;
import com.zhiyou100.video.model.Speaker;
import com.zhiyou100.video.service.SpeakerService;

@Controller
public class SpeakerController {
    @Autowired
	SpeakerService  ss;
	
      @RequestMapping("/speaker/speakerList.action")
      public String speakerList(@RequestParam(defaultValue="")String speakerSearchName,@RequestParam(defaultValue="")String speakerSearchJob,
    		  @RequestParam(value="page",required=false,defaultValue="1")Integer currentPage,Model md,HttpServletRequest req) {
    	     // currentPage = req.getParameter("page")==null ? 1:Integer.parseInt(req.getParameter("page")); 
             md.addAttribute("speakerSearchName", speakerSearchName);	       
    	     md.addAttribute("speakerSearchJop", speakerSearchJob);
    	      System.out.println(speakerSearchName+"000000"+speakerSearchJob);
    	     Page<Speaker>  list  = ss.findAllSpeakerByNameAndJop(speakerSearchJob,speakerSearchName,currentPage);
    	     md.addAttribute("page", list);
		     return "/speaker/speakerList";
	 }
    
    @RequestMapping("/speaker/addSpeaker.action")
    public String addSpeaker() {
    	 
		return "/speaker/addSpeaker";
	}  
    @RequestMapping("/speaker/addSpeaker02.action")
    public String addSpeaker02(Speaker sp) {
    	
    	 System.out.println(sp);
    	   ss.addSpeaker(sp);
    	
    	return "forward:/speaker/speakerList.action";
    }  
      
    
    @RequestMapping("/speaker/deleteSpeaker.action")
    public String deleteSpeaker(Integer id) {
    	 System.out.println(id);
    	  ss.deleteSpeakerById(id);
		return "forward:/speaker/speakerList.action";
	}
    
    @RequestMapping("/speaker/updateSpeaker01.action")
    public String updateSpeaker01(Integer id , Model md) {
    	       Speaker sp =ss.findSpeakerById(id);
    	        System.out.println(sp);
    	       md.addAttribute("speaker", sp);
		      return "/speaker/updateSpeaker";
	}
    
    
    @RequestMapping("/speaker/updateSpeaker02.action")
    public String updateSpeaker02( Speaker sp ) {
          ss.updateSpeakerById(sp);
    	return "forward:/speaker/speakerList.action";
    }
    
    
    
}
