package com.lms.api.serviceImpls;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lms.api.dto.ExamDto;
import com.lms.api.dto.StudentDto;
import com.lms.api.dto.SubjectDto;
import com.lms.api.entity.Student;
import com.lms.api.entity.Subject;
import com.lms.api.exceptions.NotFoundException;
import com.lms.api.repository.StudentRepository;
import com.lms.api.services.StudentService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{
	private final StudentRepository studentRepository;

	@Override
	public void registerSudent(StudentDto studentDto) {
		studentRepository.save(this.mapToEntity(studentDto));
	}

	@Override
	public void updateStudent(StudentDto studentDto) {
		Optional<Student> optionalStudent = studentRepository.findById(studentDto.getStudentId());
		Student student = optionalStudent.orElseThrow(()-> new NotFoundException("Student not found for given id"));
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
		// TODO Auto-generated method stub
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
	
	private Student mapToEntity(StudentDto studentDto) {
		Student student = new Student();
		student.setStudentName(studentDto.getStudentName());
		return student;
	}
	
	private StudentDto mapToDto(Student student) {
		StudentDto studentDto = new StudentDto();
		studentDto.setStudentId(student.getStudentId());
		studentDto.setStudentName(student.getStudentName());
		List<ExamDto> examDtos = new ArrayList<>();
		student.getExams().forEach(exam -> {
			ExamDto examDto = new ExamDto();
			examDto.setExamId(exam.getExamId());
			examDto.setSubject(this.mapToSubjectDto(exam.getSubject()));
			examDto.setCreatedAt(exam.getCreatedAt());
			examDto.setUpdatedAt(exam.getUpdatedAt());
			//examDto.setStudents(null);
			examDtos.add(examDto);
		});
		studentDto.setExams(examDtos);
		return studentDto;
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
