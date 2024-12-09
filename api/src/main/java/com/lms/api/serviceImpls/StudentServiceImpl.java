package com.lms.api.serviceImpls;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.lms.api.dto.ExamDto;
import com.lms.api.dto.StudentDto;
import com.lms.api.dto.StudentRequest;
import com.lms.api.dto.SubjectDto;
import com.lms.api.entity.Exam;
import com.lms.api.entity.Student;
import com.lms.api.entity.Subject;
import com.lms.api.exceptions.NotFoundException;
import com.lms.api.repository.StudentRepository;
import com.lms.api.services.StudentService;
import com.lms.api.services.SubjectService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{
	private final StudentRepository studentRepository;
	private final SubjectService subjectService;

	@Override
	public void registerSudent(StudentRequest studentRequest) {
		if (studentRequest.getSubjectsId() == null || studentRequest.getSubjectsId().isEmpty()) {
	        throw new IllegalArgumentException("SubjectsId list cannot be null or empty");
	    }

	    Student student = new Student();
	    List<Subject> subjects = studentRequest.getSubjectsId().stream()
	            .map(subjectService::getSubject)
	            .map(this::mapToSubjectEntity)
	            .collect(Collectors.toList());

	    student.setStudentName(studentRequest.getStudentName());
	    student.setSubjects(subjects);
	    studentRepository.save(student);
	}

	@Override
	public void updateStudent(StudentDto studentDto) {
		Optional<Student> optionalStudent = studentRepository.findById(studentDto.getStudentId());
		Student student = optionalStudent.orElseThrow(()-> new NotFoundException("Student not found for given id"));
		
		student.setStudentName(studentDto.getStudentName());
		studentRepository.save(student);
	}

	@Override
	public void deleteStudent(Long studentId) {
		Optional<Student> optionalStudent = studentRepository.findById(studentId);
		Student student = optionalStudent.orElseThrow(()-> new NotFoundException("Student not found for given id"));
		studentRepository.delete(student);
	}

	@Override
	public List<StudentDto> getAllStudent() {
		List<Student> students = studentRepository.findAll();
		List<StudentDto> studentDtos = new ArrayList<>();
		students.forEach(student -> {
			StudentDto studentDto = this.mapToDto(student);
			studentDtos.add(studentDto);
		});
		return studentDtos;
	}

	@Override
	public StudentDto getStudent(Long studentId) {
		Optional<Student> optionalStudent = studentRepository.findById(studentId);
		Student student = optionalStudent.orElseThrow(()-> new NotFoundException("Student not found for given id"));
		return this.mapToDto(student);
	}
	
	private StudentDto mapToDto(Student student) {
	    StudentDto studentDto = new StudentDto();
	    studentDto.setStudentId(student.getStudentId());
	    studentDto.setStudentName(student.getStudentName());

	    List<ExamDto> examDtos = (student.getExams() != null) 
	        ? student.getExams().stream().map(this::mapToExamDto).collect(Collectors.toList()) 
	        : new ArrayList<>();
	    studentDto.setExams(examDtos);

	    List<SubjectDto> subjectDtos = (student.getSubjects() != null)
	        ? student.getSubjects().stream().map(this::mapToSubjectDto).collect(Collectors.toList())
	        : new ArrayList<>();
	    studentDto.setSubjects(subjectDtos);

	    return studentDto;
	}
	
	private ExamDto mapToExamDto(Exam exam) {
        ExamDto examDto = new ExamDto();
        examDto.setExamId(exam.getExamId());
        examDto.setSubject(mapToSubjectDto(exam.getSubject()));
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
	
	private Subject mapToSubjectEntity(SubjectDto subjectDto) {
		Subject subject = new Subject();
		subject.setSubjectId(subjectDto.getSubjectId());
		subject.setSubjectName(subjectDto.getSubjectName());
		subject.setCreatedAt(subjectDto.getCreatedAt());
		subject.setUpdatedAt(subjectDto.getUpdatedAt());
		return subject;
	}

}
