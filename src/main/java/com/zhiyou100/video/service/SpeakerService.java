package com.zhiyou100.video.service;

import com.zhiyou100.video.model.Page;
import com.zhiyou100.video.model.Speaker;

public interface SpeakerService {

	Page<Speaker> findAllSpeakerByNameAndJop(String speakerSearchJop, String speakerSearchName, Integer currentPage);

	void addSpeaker(Speaker sp);

	void deleteSpeakerById(Integer id);

	Speaker findSpeakerById(Integer id);

	void updateSpeakerById(Speaker sp);

}
