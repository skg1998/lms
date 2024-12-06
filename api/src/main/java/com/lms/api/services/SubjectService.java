package com.lms.api.services;

import java.util.List;

import com.lms.api.dto.SubjectDto;

public interface SubjectService {
	public void createSubject(SubjectDto subjectDto);
	public void updateSubject(SubjectDto subjectDto);
	public void deleteSubject(Long subjectId);
	public List<SubjectDto> getAllSubject();
	public SubjectDto getSubject(Long subjectId);
}
