package com.zhiyou100.video.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhiyou100.video.model.Video;
import com.zhiyou100.video.service.StatisticsService;
@Controller
public class StatisticsController {

	
    @Autowired
	StatisticsService ss;
	
    @RequestMapping("/statistics/statisticsList.action")
	public String StatisticsList(Model md) {
		 List<Video>   v  =  ss.statisticsList();
		 StringBuilder dataBuiler= new StringBuilder();
		 StringBuilder timeBuiler= new StringBuilder();
		 
			for (int i = 0; i < v.size(); i++){
				Video video= v.get(i);
				dataBuiler.append(video.getCName());
				timeBuiler.append(video.getAverage());
				if(i != v.size() -1 ){
					dataBuiler.append(",");
					timeBuiler.append(",");
				}
			}
			
			md.addAttribute("data", dataBuiler.toString());
			md.addAttribute("times", timeBuiler.toString());
		
		   
		    return "/statistics/statisticsList";
	}
	
	
	
	
}
