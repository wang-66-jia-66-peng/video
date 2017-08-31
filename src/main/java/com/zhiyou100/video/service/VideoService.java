package com.zhiyou100.video.service;

import java.util.List;

import com.zhiyou100.video.model.Course;
import com.zhiyou100.video.model.PackageVO;
import com.zhiyou100.video.model.Page;
import com.zhiyou100.video.model.Speaker;
import com.zhiyou100.video.model.Video;

public interface VideoService {

	Page<PackageVO> findVideoBySearch(String videoKeyWord, Integer videoSearchSpeaker, Integer videoSearchCourse,
			Integer currentPage);

	List<Speaker> findSpeakerAll();

	List<Course> findCourseAll();

	void deleteVideoById(Integer id);

	Video findVideoById(Integer id);

	void addVideo(Video v);

	void updateVideo(Video v);

}
