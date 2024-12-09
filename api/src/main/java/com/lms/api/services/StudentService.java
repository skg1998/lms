package com.lms.api.services;

import java.util.List;

import com.lms.api.dto.StudentDto;
import com.lms.api.dto.StudentRequest;

public interface StudentService {
	public void registerSudent(StudentRequest studentDto);
	public void updateStudent(StudentDto studentDto);
	public void deleteStudent(Long studentId);
	public List<StudentDto> getAllStudent();
	public StudentDto getStudent(Long studentId);
}
