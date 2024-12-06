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
public class SubjectDto {
	private Long subjectId;
	private String subjectName;
	private List<StudentDto> students;
	private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
