package com.lms.api.serviceImpls;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lms.api.dto.ExamDto;
import com.lms.api.dto.SubjectDto;
import com.lms.api.entity.Exam;
import com.lms.api.entity.Subject;
import com.lms.api.exceptions.NotFoundException;
import com.lms.api.repository.ExamRepository;
import com.lms.api.services.ExamService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService{
	private final ExamRepository examRepository;

	@Override
	public void createExam(ExamDto examDto) {
		// TODO Auto-generated method stub
		examRepository.save(this.mapToEntity(examDto));
	}

	@Override
	public void updateExam(ExamDto examDto) {
		Optional<Exam> optionalExam = examRepository.findById(examDto.getExamId());
		Exam exam = optionalExam.orElseThrow(()-> new NotFoundException("Exam not found for given examid"));
		
		examRepository.save(exam);
	}

	@Override
	public void deleteExam(Long examId) {
		// TODO Auto-generated method stub
		Optional<Exam> optionalExam = examRepository.findById(examId);
		Exam exam = optionalExam.orElseThrow(()-> new NotFoundException("Exam not found for given examid"));
		
		examRepository.delete(exam);
	}

	@Override
	public List<ExamDto> getAllExam() {
		List<Exam> exams = examRepository.findAll();
		List<ExamDto> examDtos = new ArrayList<>();
		exams.forEach(exam -> {
			ExamDto examDto = this.mapToExamDto(exam);
			examDtos.add(examDto);
		});
		return examDtos;
	}

	@Override
	public ExamDto getExam(Long examId) {
		Optional<Exam> optionalExam = examRepository.findById(examId);
		Exam exam = optionalExam.orElseThrow(()-> new NotFoundException("Exam not found for given examid"));		
		return this.mapToExamDto(exam);
	}
	
	private Exam mapToEntity(ExamDto examDto) {
		Exam exam = new Exam();
		exam.setExamId(examDto.getExamId());
		exam.setSubject(this.mapToSubject(examDto.getSubject()));
//		exam.setCreatedAt(examDto.getCreatedAt());
//		exam.setUpdatedAt(examDto.getUpdatedAt());
		
		return exam;
	}
	
	private Subject mapToSubject(SubjectDto subjectDto) {
		Subject subject = new Subject();
		subject.setSubjectId(subjectDto.getSubjectId());
		subject.setSubjectName(subjectDto.getSubjectName());
		//subjectDto.setStudents(null);
		subject.setCreatedAt(subjectDto.getCreatedAt());
		subject.setUpdatedAt(subjectDto.getUpdatedAt());
		return subject;
	}
	
	private ExamDto mapToExamDto(Exam exam ) {
		ExamDto examDto = new ExamDto();
		examDto.setExamId(exam.getExamId());
		examDto.setSubject(this.mapToSubjectDto(exam.getSubject()));
		examDto.setCreatedAt(exam.getCreatedAt());
		examDto.setUpdatedAt(exam.getUpdatedAt());
		
		return examDto;
	}
	
	private SubjectDto mapToSubjectDto(Subject subject) {
		SubjectDto subjectDto = new SubjectDto();
		subjectDto.setSubjectId(subject.getSubjectId());
		subjectDto.setSubjectName(subject.getSubjectName());
		//subjectDto.setStudents(null);
		subjectDto.setCreatedAt(subject.getCreatedAt());
		subjectDto.setUpdatedAt(subject.getUpdatedAt());
		return subjectDto;
	}

}
