package com.baitapjpa.baitapvenhajpa.controller;

import com.baitapjpa.baitapvenhajpa.entity.Students;
import com.baitapjpa.baitapvenhajpa.request.SaveStudentRequest;
import com.baitapjpa.baitapvenhajpa.response.CourseResponse;
import com.baitapjpa.baitapvenhajpa.response.EnrollStudentResponse;
import com.baitapjpa.baitapvenhajpa.response.EntrollCoursesByStudentsRequest;
import com.baitapjpa.baitapvenhajpa.response.StudentResponse;
import com.baitapjpa.baitapvenhajpa.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentsController {
    @Autowired
    private StudentsService studentsService;


    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudent(){
        return ResponseEntity.ok().body(studentsService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable int id){
        return ResponseEntity.ok().body(studentsService.getStudentById(id));
    }

    @PostMapping
    public ResponseEntity<Students> saveStudents(@RequestBody SaveStudentRequest saveStudentRequest) {
        return ResponseEntity.ok().body(studentsService.saveStudents(saveStudentRequest));
    }

    @PostMapping("/{idStudent}/courses/{courseId}")
    public ResponseEntity<EnrollStudentResponse> registerCourse(@PathVariable int idStudent, @PathVariable int courseId){
        return  ResponseEntity.ok().body(studentsService.registerCourse(idStudent,courseId));
    }

    @GetMapping("/{idStudent}/courses")
    public ResponseEntity<EntrollCoursesByStudentsRequest> getEnrollmentCoursesByIdStudent(@PathVariable int idStudent){
        return ResponseEntity.ok().body(studentsService.getEnrollmentCoursesByStudentId(idStudent));
    }

}
