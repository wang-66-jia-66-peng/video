package com.zhiyou100.video.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.video.mapper.VideoMapper;
import com.zhiyou100.video.model.Video;
import com.zhiyou100.video.service.StatisticsService;
@Service
public class StatisticsServiceimpl implements StatisticsService {
       @Autowired
	   VideoMapper  vm;


	public void setVm(VideoMapper vm) {
		this.vm = vm;
	}


	


	@Override
	public List<Video> statisticsList() {
		
		return vm.statisticsList();
	}
	   
	
	
	
	
	
}
