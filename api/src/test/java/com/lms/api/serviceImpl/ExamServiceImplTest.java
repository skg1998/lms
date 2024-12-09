package com.lms.api.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.lms.api.dto.ExamDto;
import com.lms.api.dto.ExamRequest;
import com.lms.api.dto.StudentDto;
import com.lms.api.dto.SubjectDto;
import com.lms.api.entity.Exam;
import com.lms.api.entity.Subject;
import com.lms.api.exceptions.NotFoundException;
import com.lms.api.repository.ExamRepository;
import com.lms.api.serviceImpls.ExamServiceImpl;
import com.lms.api.services.StudentService;
import com.lms.api.services.SubjectService;

class ExamServiceImplTest {

    @Mock
    private ExamRepository examRepository;

    @Mock
    private SubjectService subjectService;

    @Mock
    private StudentService studentService;

    @InjectMocks
    private ExamServiceImpl examService;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateExam() {
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setSubjectName("Mathematics");
        
        Subject subject = new Subject();
        subject.setSubjectId(1L);
        subject.setSubjectName("Mathematics");

        ExamRequest examRequest = new ExamRequest();
        examRequest.setSubjectId(1L);

        Mockito.when(subjectService.getSubject(1L)).thenReturn(subjectDto);
        Mockito.when(examRepository.save(Mockito.any(Exam.class))).thenAnswer(invocation -> invocation.getArgument(0));

        examService.createExam(examRequest);

        Mockito.verify(examRepository, Mockito.times(1)).save(Mockito.any(Exam.class));
    }

    @Test
    void testGetExamById() {
        Exam exam = new Exam();
        exam.setExamId(1L);
        exam.setSubject(new Subject());
        exam.setCreatedAt(LocalDateTime.now());

        Mockito.when(examRepository.findById(1L)).thenReturn(Optional.of(exam));

        ExamDto result = examService.getExam(1L);

        assertEquals(1L, result.getExamId());
    }

    @Test
    void testDeleteExam_NotFound() {
        Mockito.when(examRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> examService.deleteExam(1L));
    }

    @Test
    void testRegisterForExam() {
        Exam exam = new Exam();
        exam.setExamId(1L);
        exam.setStudents(new ArrayList<>());

        StudentDto studentDto = new StudentDto();
        studentDto.setStudentId(1L);
        studentDto.setStudentName("John Doe");

        Mockito.when(examRepository.findById(1L)).thenReturn(Optional.of(exam));
        Mockito.when(studentService.getStudent(1L)).thenReturn(studentDto);
        Mockito.when(examRepository.save(Mockito.any(Exam.class))).thenReturn(exam);

        examService.registerForExam(1L, 1L);

        assertEquals(1, exam.getStudents().size());
        Mockito.verify(examRepository, Mockito.times(1)).save(exam);
    }
}
