package com.baitapjpa.baitapvenhajpa.service;

import com.baitapjpa.baitapvenhajpa.entity.Courses;
import com.baitapjpa.baitapvenhajpa.entity.Enrollment;
import com.baitapjpa.baitapvenhajpa.entity.Students;
import com.baitapjpa.baitapvenhajpa.repository.CoursesRepository;
import com.baitapjpa.baitapvenhajpa.repository.EnrollmentRepository;
import com.baitapjpa.baitapvenhajpa.repository.StudentsRepository;
import com.baitapjpa.baitapvenhajpa.request.SaveStudentRequest;
import com.baitapjpa.baitapvenhajpa.response.EnrollStudentResponse;
import com.baitapjpa.baitapvenhajpa.response.EntrollCoursesByStudentsRequest;
import com.baitapjpa.baitapvenhajpa.response.StudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentsService {
    @Autowired
    private StudentsRepository studentsRepository;
    @Autowired
    private CoursesRepository coursesRepository;
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public List<StudentResponse> getAllStudents() {
        return studentsRepository.findAll().stream().map(s -> new StudentResponse(s.getId(),s.getName(),s.getEmail())).toList();
    }

    public StudentResponse getStudentById(int id){
        Students students = studentsRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
        StudentResponse  studentResponse = new StudentResponse();
        studentResponse.setId(students.getId());
        studentResponse.setName(students.getName());
        studentResponse.setEmail(students.getEmail());

        return studentResponse;
    }


    public Students saveStudents(SaveStudentRequest saveStudentRequest) {
        Students students = new Students();
        students.setName(saveStudentRequest.getName());
        students.setEmail(saveStudentRequest.getEmail());

        Students savedStudents = studentsRepository.save(students);
        return savedStudents;
    }


    public EnrollStudentResponse registerCourse(int idStudent, int idCourse) {
        Students students = studentsRepository.findById(idStudent)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
        Courses courses = coursesRepository.findById(idCourse)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));

        boolean exists = enrollmentRepository.findByStudentsIdAndCoursesId(idStudent, idCourse).isPresent();
        if (exists) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Student already enrolled in this course");
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setStudents(students);
        enrollment.setCourses(courses);
        enrollmentRepository.save(enrollment);

        EnrollStudentResponse  enrollStudentResponse = new EnrollStudentResponse();
        enrollStudentResponse.setId(enrollment.getStudents().getId());
        enrollStudentResponse.setName(enrollment.getStudents().getName());
        enrollStudentResponse.setEmail(enrollment.getStudents().getEmail());
        enrollStudentResponse.setCourses(enrollment.getCourses());

        return enrollStudentResponse;
    }

    public EntrollCoursesByStudentsRequest getEnrollmentCoursesByStudentId(int idStudent) {
        List<Enrollment> listEnrollmentByIdStudent = enrollmentRepository.findByStudentsId(idStudent)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Courses's student not found"));

        Students students = null;
        List<Courses> listCourses = new ArrayList<>();

        if (listEnrollmentByIdStudent.size() > 0) {
             students = listEnrollmentByIdStudent.get(0).getStudents();
            for (Enrollment enrollment : listEnrollmentByIdStudent) {
                listCourses.add(enrollment.getCourses());
            }
        } else {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Courses's student not found");
        }

        EntrollCoursesByStudentsRequest result = new EntrollCoursesByStudentsRequest();
        if (listCourses.size() > 0 && students != null) {
            result.setId(students.getId());
            result.setName(students.getName());
            result.setEmail(students.getEmail());
            result.setListCourses(listCourses);
        }
        return result;
    }
}
