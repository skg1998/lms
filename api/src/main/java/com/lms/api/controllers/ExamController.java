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
import com.lms.api.dto.ExamDto;
import com.lms.api.dto.SuccessResponse;
import com.lms.api.services.ExamService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/exam")
@RequiredArgsConstructor
public class ExamController {
	private final ExamService examService;
	
	@GetMapping("/{examId}")
    public ResponseEntity<AppResponse> getExamById(@PathVariable Long examId) {
        ExamDto examDto = examService.getExam(examId);
        return responseMaker("fetch exam detail successfully", HttpStatus.OK, examDto);  
    }

    @GetMapping("/")
    public ResponseEntity<AppResponse> getAllExam() {
        List<ExamDto> examDtos = examService.getAllExam();
        return responseMaker("fetch all exam detail successfully", HttpStatus.OK, examDtos);  
    }

    @PostMapping("/")
    public ResponseEntity<AppResponse> createExam(@RequestBody ExamDto examDto) {
    	examService.createExam(examDto);
        return responseMaker("create new student successfully", HttpStatus.OK, null);  
    }

    @PutMapping("/")
    public ResponseEntity<AppResponse> updateExam(@RequestBody ExamDto examDto) {
        examService.updateExam(examDto);
        return responseMaker("update exam successfully", HttpStatus.OK, null);  
    }

    @DeleteMapping("/{examId}")
    public ResponseEntity<AppResponse> deleteExam(@PathVariable Long examId) {
    	examService.deleteExam(examId);
        return responseMaker("delete exam successfully", HttpStatus.OK, null);
    }
    
    @SuppressWarnings("rawtypes")
	private <T> ResponseEntity<AppResponse> responseMaker(String message, HttpStatus httpStatus, T data){
		@SuppressWarnings("unchecked")
		SuccessResponse<T> successResponse = new SuccessResponse(message, data);
        AppResponse response = new AppResponse(true, successResponse, null);
        return response.toResponseEntity(httpStatus);
	}
}
