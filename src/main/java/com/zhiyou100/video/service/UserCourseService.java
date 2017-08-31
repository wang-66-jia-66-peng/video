package com.zhiyou100.video.service;

import java.util.List;

import com.zhiyou100.video.model.Course;
import com.zhiyou100.video.model.Speaker;
import com.zhiyou100.video.model.Subject;
import com.zhiyou100.video.model.Video;

public interface UserCourseService {

	Subject findSubjectById(Integer subjectId);

	List<Course> findAllCourseAndVideo(Integer subjectId);

	Video findVideoAllByid(Integer videoId);

	Course findCourseById(Integer courseId);

	Speaker findSpeakerById(Integer speakerId);

	List<Video> findVideoAllandSpeakerNameById(Integer videoId);

}
