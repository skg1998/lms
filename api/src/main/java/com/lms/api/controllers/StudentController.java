package com.lms.api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.api.dto.AppResponse;
import com.lms.api.dto.StudentDto;
import com.lms.api.dto.SuccessResponse;
import com.lms.api.services.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/students")
@RequiredArgsConstructor
public class StudentController {
	private final StudentService studentService;
    
    @GetMapping("/{studentId}")
    public ResponseEntity<AppResponse> getStudentById(@PathVariable Long studentId) {
        StudentDto studentDto = studentService.getStudent(studentId);
        return responseMaker("fetch student detail successfully", HttpStatus.OK, studentDto);  
    }

    @GetMapping("/")
    public ResponseEntity<AppResponse> getAllStudent() {
        List<StudentDto> studentDtos = studentService.getAllStudent();
        return responseMaker("fetch all student detail successfully", HttpStatus.OK, studentDtos);  
    }

    @PostMapping("/")
    public ResponseEntity<AppResponse> registerStudent(@RequestBody StudentDto studentDto) {
    	studentService.registerSudent(studentDto);
        return responseMaker("create new student successfully", HttpStatus.OK, null);  
    }

    @PutMapping("/")
    public ResponseEntity<AppResponse> updateStudent(@RequestBody StudentDto studentDto) {
        studentService.updateStudent(studentDto);
        return responseMaker("update student successfully", HttpStatus.OK, null);  
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<AppResponse> deleteStudent(@PathVariable Long studentId) {
    	studentService.deleteStudent(studentId);
        return responseMaker("delete student successfully", HttpStatus.OK, null);
    }
    
    @SuppressWarnings("rawtypes")
	private <T> ResponseEntity<AppResponse> responseMaker(String message, HttpStatus httpStatus, T data){
		@SuppressWarnings("unchecked")
		SuccessResponse<T> successResponse = new SuccessResponse(message, data);
        AppResponse response = new AppResponse(true, successResponse, null);
        return response.toResponseEntity(httpStatus);
	}

}
