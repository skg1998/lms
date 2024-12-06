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
import com.lms.api.dto.SubjectDto;
import com.lms.api.dto.SuccessResponse;
import com.lms.api.services.SubjectService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/subject")
@RequiredArgsConstructor
public class SubjectController {
	private final SubjectService subjectService;
	
	@GetMapping("/{subjectId}")
    public ResponseEntity<AppResponse> getSubjectById(@PathVariable Long subjectId) {
        SubjectDto subjectDto = subjectService.getSubject(subjectId);
        return responseMaker("fetch Subject detail successfully", HttpStatus.OK, subjectDto);  
    }

    @GetMapping("/")
    public ResponseEntity<AppResponse> getAllSubject() {
        List<SubjectDto> subjectDtos = subjectService.getAllSubject();
        return responseMaker("fetch all Subject detail successfully", HttpStatus.OK, subjectDtos);  
    }

    @PostMapping("/")
    public ResponseEntity<AppResponse> registerSubject(@RequestBody SubjectDto SubjectDto) {
    	subjectService.createSubject(SubjectDto);
        return responseMaker("create new Subject successfully", HttpStatus.OK, null);  
    }

    @PutMapping("/")
    public ResponseEntity<AppResponse> updateSubject(@RequestBody SubjectDto SubjectDto) {
    	subjectService.updateSubject(SubjectDto);
        return responseMaker("update Subject successfully", HttpStatus.OK, null);  
    }

    @DeleteMapping("/{subjectId}")
    public ResponseEntity<AppResponse> deleteSubject(@PathVariable Long subjectId) {
    	subjectService.deleteSubject(subjectId);
        return responseMaker("delete Subject successfully", HttpStatus.OK, null);
    }
    
    @SuppressWarnings("rawtypes")
	private <T> ResponseEntity<AppResponse> responseMaker(String message, HttpStatus httpStatus, T data){
		@SuppressWarnings("unchecked")
		SuccessResponse<T> successResponse = new SuccessResponse(message, data);
        AppResponse response = new AppResponse(true, successResponse, null);
        return response.toResponseEntity(httpStatus);
	}
}
