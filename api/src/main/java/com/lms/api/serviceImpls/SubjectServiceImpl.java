package com.lms.api.serviceImpls;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.lms.api.dto.SubjectDto;
import com.lms.api.entity.Subject;
import com.lms.api.exceptions.NotFoundException;
import com.lms.api.repository.SubjectRepository;
import com.lms.api.services.SubjectService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService{
	private final SubjectRepository subjectRepository;
	
	@Override
	public void createSubject(SubjectDto subjectDto) {
		subjectRepository.save(this.mapToSubjectEntity(subjectDto));
	}

	@Override
    public void updateSubject(SubjectDto subjectDto) {
        Subject subject = subjectRepository.findById(subjectDto.getSubjectId())
                .orElseThrow(() -> new NotFoundException("Subject not found for the given subject ID"));

        subject.setSubjectName(subjectDto.getSubjectName());
        subjectRepository.save(subject);
    }

	@Override
    public void deleteSubject(Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new NotFoundException("Subject not found for the given subject ID"));

        subjectRepository.delete(subject);
    }

	@Override
    public List<SubjectDto> getAllSubject() {
        List<Subject> subjects = subjectRepository.findAll();
        return subjects.stream().map(this::mapToSubjectDto).collect(Collectors.toList());
    }

	@Override
    public SubjectDto getSubject(Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new NotFoundException("Subject not found for the given subject ID"));
        return mapToSubjectDto(subject);
    }
	
	private Subject mapToSubjectEntity(SubjectDto subjectDto) {
        Subject subject = new Subject();
        subject.setSubjectName(subjectDto.getSubjectName());
        subject.setCreatedAt(subjectDto.getCreatedAt());
        subject.setUpdatedAt(subjectDto.getUpdatedAt());
        return subject;
    }
	
	private SubjectDto mapToSubjectDto(Subject subject) {
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setSubjectId(subject.getSubjectId());
        subjectDto.setSubjectName(subject.getSubjectName());
        subjectDto.setCreatedAt(subject.getCreatedAt());
        subjectDto.setUpdatedAt(subject.getUpdatedAt());
        return subjectDto;
    }

}
