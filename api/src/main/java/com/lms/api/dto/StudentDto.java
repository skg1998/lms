package com.lms.api.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
	private Long studentId;
	private String studentName;
	private List<SubjectDto> subjects;
	private List<ExamDto> exams;
	private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
