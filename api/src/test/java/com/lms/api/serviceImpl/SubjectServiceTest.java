package com.lms.api.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.lms.api.dto.SubjectDto;
import com.lms.api.entity.Subject;
import com.lms.api.exceptions.NotFoundException;
import com.lms.api.repository.SubjectRepository;
import com.lms.api.serviceImpls.SubjectServiceImpl;

class SubjectServiceTest {

    @Mock
    private SubjectRepository subjectRepository;

    @InjectMocks
    private SubjectServiceImpl subjectService;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateSubject() {
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setSubjectName("Mathematics");
        
        Subject subject = new Subject();
        subject.setSubjectName("Mathematics");

        Mockito.when(subjectRepository.save(Mockito.any(Subject.class))).thenReturn(subject);

        subjectService.createSubject(subjectDto);

        Mockito.verify(subjectRepository, Mockito.times(1)).save(Mockito.any(Subject.class));
    }

    @Test
    void testGetSubjectById() {
        Subject subject = new Subject();
        subject.setSubjectId(1L);
        subject.setSubjectName("Mathematics");

        Mockito.when(subjectRepository.findById(1L)).thenReturn(Optional.of(subject));

        SubjectDto result = subjectService.getSubject(1L);

        assertEquals("Mathematics", result.getSubjectName());
        assertEquals(1L, result.getSubjectId());
    }

    @Test
    void testDeleteSubject_Success() {
        Subject subject = new Subject();
        subject.setSubjectId(1L);

        Mockito.when(subjectRepository.findById(1L)).thenReturn(Optional.of(subject));

        subjectService.deleteSubject(1L);

        Mockito.verify(subjectRepository, Mockito.times(1)).delete(subject);
    }
}

