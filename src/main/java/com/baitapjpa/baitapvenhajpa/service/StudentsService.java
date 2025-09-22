package com.baitapjpa.baitapvenhajpa.service;

import com.baitapjpa.baitapvenhajpa.request.SaveStudentRequest;
import com.baitapjpa.baitapvenhajpa.response.EnrollStudentResponse;
import com.baitapjpa.baitapvenhajpa.response.EntrollCoursesByStudentsRequest;
import com.baitapjpa.baitapvenhajpa.response.SaveStudentsResponse;
import com.baitapjpa.baitapvenhajpa.response.StudentResponse;

import java.util.List;

public interface StudentsService {
    List<StudentResponse> getAllStudents();
    StudentResponse getStudentById(int id);
    SaveStudentsResponse saveStudents(SaveStudentRequest saveStudentRequest);
    EnrollStudentResponse registerCourse(int idStudent, int idCourse);
    EntrollCoursesByStudentsRequest getEnrollmentCoursesByStudentId(int idStudent);
}
