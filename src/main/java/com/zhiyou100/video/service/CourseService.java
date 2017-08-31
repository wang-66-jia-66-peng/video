package com.zhiyou100.video.service;

import java.util.List;

import com.zhiyou100.video.model.Course;
import com.zhiyou100.video.model.Page;
import com.zhiyou100.video.model.Subject;

public interface CourseService {

	Page<Course> findAllCourse(Integer page);

	List<Subject> findAllSubject();

	void addCourse(Course cu);

	Course findCourseById(Integer id);

	void updateCourse(Course co);

	void deleteCourse(Integer id);

}
