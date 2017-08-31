package com.zhiyou100.video.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.video.mapper.CourseMapper;
import com.zhiyou100.video.mapper.SpeakerMapper;
import com.zhiyou100.video.mapper.VideoMapper;
import com.zhiyou100.video.model.Course;
import com.zhiyou100.video.model.PackageVO;
import com.zhiyou100.video.model.Page;
import com.zhiyou100.video.model.Speaker;
import com.zhiyou100.video.model.Video;
import com.zhiyou100.video.model.VideoExample;
import com.zhiyou100.video.service.VideoService;
@Service
public class VideoSeviceImpl implements VideoService {
    @Autowired
	VideoMapper  vm;
    @Autowired
    CourseMapper  cm;
    @Autowired
    SpeakerMapper  sm;
    
	public void setVm(VideoMapper vm) {
		this.vm = vm;
	}
    
	
	
	public void setCm(CourseMapper cm) {
		this.cm = cm;
	}



	public void setSm(SpeakerMapper sm) {
		this.sm = sm;
	}



	@Override
	public Page<PackageVO> findVideoBySearch(String videoKeyWord, Integer videoSearchSpeaker, Integer videoSearchCourse,
			Integer currentPage) {
		 Page<PackageVO> pp = new Page<>();
		 pp.setSize(10);
		 pp.setPage(currentPage);
		 PackageVO  pv = new PackageVO();
		 pv.setVideoKeyWord(videoKeyWord);
		 pv.setCurrentPage((currentPage-1)*10);
		 pv.setVideoSearchCourse(videoSearchCourse);
		 pv.setVideoSearchSpeaker(videoSearchSpeaker);
		 
		 List<PackageVO> rows  =  vm.findVideoBySearch(pv);
		 
		 Integer  total = vm.findVideoBySearchCount(pv);
		 
		 pp.setRows(rows); 
		 pp.setTotal(total);
		 return pp;
	}

	@Override
	public List<Speaker> findSpeakerAll() {
		 
	List<Speaker>  list  = sm.selectByExample(null) ;
		 
		return list;
	}

	@Override
	public List<Course> findCourseAll() {
		    
	 List<Course>  list = cm.selectByExample(null);
   
	  return list;
	}



	@Override
	public void deleteVideoById(Integer id) {
		  VideoExample  example = new VideoExample();
		   
		  example.createCriteria().andIdEqualTo(id);
		
          vm.deleteByExample(example);
          
	}



	@Override
	public Video findVideoById(Integer id) {
		   Video video = vm.selectByPrimaryKey(id);
		   return video;
	}



	@Override
	public void addVideo(Video v) {
		  vm.insert(v);
	}



	@Override
	public void updateVideo(Video v) {
        vm.updateByPrimaryKeySelective(v);
        
	}
	
	
	
	
	
	
}
