package com.lms.api.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.api.dto.AppResponse;
import com.lms.api.dto.SuccessResponse;
import com.lms.api.services.EasterEggService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/hidden-feature")
@RequiredArgsConstructor
public class EasterEggController {
	private final EasterEggService easterEggService;
	
	@GetMapping("/{number}")
    public ResponseEntity<AppResponse> getRandomFact(@PathVariable("number") int number) {
        String fact = easterEggService.getRandomNumberFact(number);
        return responseMaker("get number fact successfully", HttpStatus.OK, fact);  
    }

	@SuppressWarnings("rawtypes")
	private <T> ResponseEntity<AppResponse> responseMaker(String message, HttpStatus httpStatus, T data){
		@SuppressWarnings("unchecked")
		SuccessResponse<T> successResponse = new SuccessResponse(message, data);
        AppResponse response = new AppResponse(true, successResponse, null);
        return response.toResponseEntity(httpStatus);
	}
}
