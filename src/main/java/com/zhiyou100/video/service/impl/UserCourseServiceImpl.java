package com.zhiyou100.video.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.video.mapper.CourseMapper;
import com.zhiyou100.video.mapper.SpeakerMapper;
import com.zhiyou100.video.mapper.SubjectMapper;
import com.zhiyou100.video.mapper.VideoMapper;
import com.zhiyou100.video.model.Course;
import com.zhiyou100.video.model.Speaker;
import com.zhiyou100.video.model.Subject;
import com.zhiyou100.video.model.Video;
import com.zhiyou100.video.service.UserCourseService;
@Service
public class UserCourseServiceImpl implements UserCourseService {
     @Autowired
	 CourseMapper cm;
     @Autowired
     SubjectMapper  sm;
     @Autowired
     VideoMapper vm;
     @Autowired
     SpeakerMapper skm;
     
     
	@Override
	public Subject findSubjectById(Integer subjectId) {
	    Subject key = sm.selectByPrimaryKey(subjectId);
		return key;
	}

	@Override
	public List<Course> findAllCourseAndVideo(Integer subject) {
		
		return cm.findAllCourseAndVideo(subject);
	}

	@Override
	public Video findVideoAllByid(Integer videoId) {
		
		Video v = vm.selectByPrimaryKey(videoId);
		return v;
	}

	@Override
	public Course findCourseById(Integer courseId) {
		  
		return cm.selectByPrimaryKey(courseId);
	}

	@Override
	public Speaker findSpeakerById(Integer speakerId) {
		
		return skm.selectByPrimaryKey(speakerId);
	}

	@Override
	public List<Video> findVideoAllandSpeakerNameById(Integer videoId) {
		
		return vm.findVideoAllandSpeakerNameById(videoId);
	}
	 
	
	
}
