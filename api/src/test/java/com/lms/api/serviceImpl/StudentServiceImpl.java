package com.lms.api.serviceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.lms.api.dto.StudentDto;
import com.lms.api.dto.StudentRequest;
import com.lms.api.dto.SubjectDto;
import com.lms.api.entity.Student;
import com.lms.api.exceptions.NotFoundException;
import com.lms.api.repository.StudentRepository;
import com.lms.api.serviceImpls.StudentServiceImpl;
import com.lms.api.services.SubjectService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private SubjectService subjectService;

    @InjectMocks
    private StudentServiceImpl studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterStudent_Success() {
        StudentRequest studentRequest = new StudentRequest();
        studentRequest.setStudentName("John Doe");
        studentRequest.setSubjectsId(List.of(1L, 2L));

        SubjectDto subjectDto1 = new SubjectDto();
        subjectDto1.setSubjectId(1L);
        subjectDto1.setSubjectName("Math");
        
        SubjectDto subjectDto2 = new SubjectDto();
        subjectDto2.setSubjectId(2L);
        subjectDto2.setSubjectName("Science");
        
        when(subjectService.getSubject(1L)).thenReturn(subjectDto1);
        when(subjectService.getSubject(2L)).thenReturn(subjectDto2);

        studentService.registerSudent(studentRequest);

        ArgumentCaptor<Student> captor = ArgumentCaptor.forClass(Student.class);
        verify(studentRepository).save(captor.capture());

        Student savedStudent = captor.getValue();
        assertEquals("John Doe", savedStudent.getStudentName());
        assertEquals(2, savedStudent.getSubjects().size());
    }

    @Test
    void testGetAllStudents_Success() {
        List<Student> students = new ArrayList<>();
        Student student = new Student();
        student.setStudentId(1L);
        student.setStudentName("Jane Doe");
        students.add(student);

        when(studentRepository.findAll()).thenReturn(students);
        
        List<StudentDto> result = studentService.getAllStudent();
        
        assertEquals(1, result.size());
        assertEquals("Jane Doe", result.get(0).getStudentName());
    }

    @Test
    void testGetStudent_Success() {
        Student student = new Student();
        student.setStudentId(1L);
        student.setStudentName("Jane Doe");

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        StudentDto result = studentService.getStudent(1L);

        assertNotNull(result);
        assertEquals(1L, result.getStudentId());
        assertEquals("Jane Doe", result.getStudentName());
    }

    @Test
    void testGetStudent_NotFound() {
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> studentService.getStudent(1L));
    }

    @Test
    void testUpdateStudent_Success() {
        StudentDto studentDto = new StudentDto();
        studentDto.setStudentId(1L);
        studentDto.setStudentName("Updated Name");

        Student student = new Student();
        student.setStudentId(1L);
        student.setStudentName("Old Name");

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        studentService.updateStudent(studentDto);

        verify(studentRepository).save(student);
        assertEquals("Updated Name", student.getStudentName());
    }

    @Test
    void testUpdateStudent_NotFound() {
        StudentDto studentDto = new StudentDto();
        studentDto.setStudentId(1L);

        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> studentService.updateStudent(studentDto));
    }

    @Test
    void testDeleteStudent_Success() {
        Student student = new Student();
        student.setStudentId(1L);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        studentService.deleteStudent(1L);

        verify(studentRepository).delete(student);
    }

    @Test
    void testDeleteStudent_NotFound() {
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> studentService.deleteStudent(1L));
    }
}
