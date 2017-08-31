package com.zhiyou100.video.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.video.mapper.CourseMapper;
import com.zhiyou100.video.mapper.SubjectMapper;
import com.zhiyou100.video.model.Course;
import com.zhiyou100.video.model.Page;
import com.zhiyou100.video.model.Subject;
import com.zhiyou100.video.service.CourseService;
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
	CourseMapper  cm;
     @Autowired 
    SubjectMapper sm;
     
     
	public void setSm(SubjectMapper sm) {
		this.sm = sm;
	}

	public void setCm(CourseMapper cm) {
		this.cm = cm;
	}

	@Override
	public Page<Course> findAllCourse(Integer page) {
		  Page<Course> pc = new  Page<>();
		    pc.setPage(page);
		    pc.setSize(5);
		    
		    List<Course> rows = cm.findAllCourse((page-1)*5);
		    System.out.println(rows);
		    int total = cm.findAllCourseCount();
		    
		    pc.setTotal(total);
		    pc.setRows(rows); 
		    System.out.println(total);
		    
		    return pc;
	}

	@Override
	public List<Subject> findAllSubject() {
		List<Subject> list = sm.selectByExample(null);
		 return list;
	}

	@Override
	public void addCourse(Course cu) {
		
		  cm.insertSelective(cu);
		
	}

	@Override
	public Course findCourseById(Integer id) {
		
		return cm.selectByPrimaryKey(id);
	}

	@Override
	public void updateCourse(Course co) {
		
		cm.updateByPrimaryKeySelective(co);
		
	}

	@Override
	public void deleteCourse(Integer id) {
		cm.deleteByPrimaryKey(id);
		
	}
	
	
	
}
