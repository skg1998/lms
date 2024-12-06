package com.lms.api.serviceImpls;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		Optional<Subject> optionalSubject = subjectRepository.findById(subjectDto.getSubjectId());
		Subject subject = optionalSubject.orElseThrow(()-> new NotFoundException("Subject not found for given subjectid"));
		
		subjectRepository.save(subject);
	}

	@Override
	public void deleteSubject(Long subjectId) {
		Optional<Subject> optionalSubject = subjectRepository.findById(subjectId);
		Subject subject = optionalSubject.orElseThrow(()-> new NotFoundException("Subject not found for given subjectid"));
		
		subjectRepository.delete(subject);
	}

	@Override
	public List<SubjectDto> getAllSubject() {
		List<Subject> subjects = subjectRepository.findAll();
		List<SubjectDto> subjectDtos = new ArrayList<>();
		subjects.forEach(subject -> {
			SubjectDto subjectDto = this.mapToSubjectDto(subject);
			subjectDtos.add(subjectDto);
		});
		return subjectDtos;
	}

	@Override
	public SubjectDto getSubject(Long subjectId) {
		Optional<Subject> optionalSubject = subjectRepository.findById(subjectId);
		Subject subject = optionalSubject.orElseThrow(()-> new NotFoundException("Subject not found for given subjectid"));
		
		return this.mapToSubjectDto(subject);
	}
	
	private Subject mapToSubjectEntity(SubjectDto subjectDto) {
		Subject subject = new Subject();
		subject.setSubjectId(subjectDto.getSubjectId());
		subject.setSubjectName(subjectDto.getSubjectName());
		//subjectDto.setStudents(null);
		subject.setCreatedAt(subjectDto.getCreatedAt());
		subject.setUpdatedAt(subjectDto.getUpdatedAt());
		return subject;
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
