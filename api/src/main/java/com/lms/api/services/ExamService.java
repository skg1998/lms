package com.lms.api.services;

import java.util.List;

import com.lms.api.dto.ExamDto;

public interface ExamService {
	public void createExam(ExamDto examDto);
	public void updateExam(ExamDto examDto);
	public void deleteExam(Long examId);
	public List<ExamDto> getAllExam();
	public ExamDto getExam(Long examId);
}
