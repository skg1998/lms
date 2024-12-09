package com.lms.api.services;

import java.util.List;

import com.lms.api.dto.ExamDto;
import com.lms.api.dto.ExamRequest;
import com.lms.api.dto.ExamUpdateDto;

public interface ExamService {
	public void createExam(ExamRequest examRequest);
	public void updateExam(ExamUpdateDto examUpdateDto);
	public void deleteExam(Long examId);
	public List<ExamDto> getAllExam();
	public ExamDto getExam(Long examId);
	public void registerForExam(long examId, long studentId);
}
