package com.zhiyou100.video.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.video.mapper.SpeakerMapper;
import com.zhiyou100.video.model.Page;
import com.zhiyou100.video.model.Speaker;
import com.zhiyou100.video.service.SpeakerService;
@Service
public class SpeakerServiceImpl implements SpeakerService {
      @Autowired
	  SpeakerMapper   sm;

	public void setSm(SpeakerMapper sm) {
		this.sm = sm;
	}


	@Override
	public Page<Speaker> findAllSpeakerByNameAndJop(String speakerSearchJop, String speakerSearchName,
			Integer current) {
		  Page<Speaker> ps = new Page<>();
		   ps.setSize(5);
		   ps.setPage(current);
		   Speaker speaker = new Speaker();
		   
		   speaker.setSpeakerName(speakerSearchName);
		   
		   speaker.setSpeakerJob(speakerSearchJop);
		  
		   int total = sm.findAllSpeakerByNameAndJopCount(speaker);
		   
		   System.out.println(total);
		   ps.setTotal(total);
		   int currentPage=(current-1)*5;
		   speaker.setCurrentPage(currentPage);
		   
		   List<Speaker> rows=sm.findAllSpeakerByNameAndJop(speaker);
		   ps.setRows(rows);
		   return ps;
	}


	@Override
	public void addSpeaker(Speaker sp) {
         sm.insertSelective(sp);		
	}


	@Override
	public void deleteSpeakerById(Integer id) {
         sm.deleteByPrimaryKey(id);	
	}




	@Override
	public Speaker findSpeakerById(Integer id) {
		 Speaker speaker = sm.selectByPrimaryKey(id);
		 return speaker;
	}

    
	@Override
	public void updateSpeakerById(Speaker sp) {
          sm.updateByPrimaryKeySelective(sp);		
	}
	  
	
	
}
