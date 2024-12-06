package com.lms.api.services;

import java.util.List;

import com.lms.api.dto.StudentDto;

public interface StudentService {
	public void registerSudent(StudentDto studentDto);
	public void updateStudent(StudentDto studentDto);
	public void deleteStudent(Long studentId);
	public List<StudentDto> getAllStudent();
	public StudentDto getStudent(Long studentId);
}
