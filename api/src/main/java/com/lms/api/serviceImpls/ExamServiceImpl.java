package com.lms.api.serviceImpls;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.lms.api.dto.ExamDto;
import com.lms.api.dto.ExamRequest;
import com.lms.api.dto.ExamUpdateDto;
import com.lms.api.dto.StudentDto;
import com.lms.api.dto.SubjectDto;
import com.lms.api.entity.Exam;
import com.lms.api.entity.Student;
import com.lms.api.entity.Subject;
import com.lms.api.exceptions.NotFoundException;
import com.lms.api.repository.ExamRepository;
import com.lms.api.services.ExamService;
import com.lms.api.services.StudentService;
import com.lms.api.services.SubjectService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService{
	private final ExamRepository examRepository;
	private final SubjectService subjectService;
	private final StudentService studentService;

	@Override
	public void createExam(ExamRequest examRequest) {
		SubjectDto subjectDto = subjectService.getSubject(examRequest.getSubjectId());
		Subject subject = this.mapToSubject(subjectDto);
		
		Exam exam = new Exam();
		exam.setSubject(subject);
		examRepository.save(exam);
	}

	@Override
	public void updateExam(ExamUpdateDto updateDto) {
		Optional<Exam> optionalExam = examRepository.findById(updateDto.getExamId());
		Exam exam = optionalExam.orElseThrow(()-> new NotFoundException("Exam not found for given examid"));
		
		SubjectDto subjectDto = subjectService.getSubject(updateDto.getSubjectId());
		Subject subject = this.mapToSubject(subjectDto);
		
		exam.setSubject(subject);
		examRepository.save(exam);
	}

	@Override
	public void deleteExam(Long examId) {
		Optional<Exam> optionalExam = examRepository.findById(examId);
		Exam exam = optionalExam.orElseThrow(()-> new NotFoundException("Exam not found for given examid"));
		examRepository.delete(exam);
	}

	@Override
    public List<ExamDto> getAllExam() {
        List<Exam> exams = examRepository.findAll();
        return exams.stream().map(this::mapToExamDto).collect(Collectors.toList());
    }

	@Override
	public ExamDto getExam(Long examId) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new NotFoundException("Exam not found for given exam ID"));
        return mapToExamDto(exam);
    }
	
	@Override
	public void registerForExam(long examId, long studentId) {
		Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new NotFoundException("Exam not found for given exam ID"));

        StudentDto studentDto = studentService.getStudent(studentId);
        Student student = mapToStudentEntity(studentDto);

        if (!exam.getStudents().contains(student)) {
            exam.getStudents().add(student);
        }

        examRepository.save(exam);
	}
	
	private Subject mapToSubject(SubjectDto subjectDto) {
        Subject subject = new Subject();
        subject.setSubjectId(subjectDto.getSubjectId());
        subject.setSubjectName(subjectDto.getSubjectName());
        subject.setCreatedAt(subjectDto.getCreatedAt());
        subject.setUpdatedAt(subjectDto.getUpdatedAt());
        return subject;
    }
	
	private ExamDto mapToExamDto(Exam exam) {
        ExamDto examDto = new ExamDto();
        examDto.setExamId(exam.getExamId());
        examDto.setSubject(mapToSubjectDto(exam.getSubject()));
        examDto.setStudents(
                exam.getStudents() != null
                    ? exam.getStudents().stream().map(this::mapToStudentDto).collect(Collectors.toList())
                    : new ArrayList<>()
            );
        examDto.setCreatedAt(exam.getCreatedAt());
        examDto.setUpdatedAt(exam.getUpdatedAt());
        return examDto;
    }
	
	private SubjectDto mapToSubjectDto(Subject subject) {
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setSubjectId(subject.getSubjectId());
        subjectDto.setSubjectName(subject.getSubjectName());
        subjectDto.setCreatedAt(subject.getCreatedAt());
        subjectDto.setUpdatedAt(subject.getUpdatedAt());
        return subjectDto;
    }

	private Student mapToStudentEntity(StudentDto studentDto) {
        Student student = new Student();
        student.setStudentId(studentDto.getStudentId());
        student.setStudentName(studentDto.getStudentName());
        student.setCreatedAt(studentDto.getCreatedAt());
        student.setUpdatedAt(studentDto.getUpdatedAt());
        return student;
    }
	
	private StudentDto mapToStudentDto(Student student) {
		StudentDto studentDto = new StudentDto();
		studentDto.setStudentId(student.getStudentId());
		studentDto.setStudentName(student.getStudentName());
		return studentDto;
	}
}
