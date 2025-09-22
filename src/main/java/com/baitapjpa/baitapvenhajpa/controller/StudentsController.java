package com.baitapjpa.baitapvenhajpa.controller;

import com.baitapjpa.baitapvenhajpa.request.SaveStudentRequest;
import com.baitapjpa.baitapvenhajpa.response.*;
import com.baitapjpa.baitapvenhajpa.service.CoursesService;
import com.baitapjpa.baitapvenhajpa.service.Imp.StudentsServiceImp;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentsController {
    @Autowired
    private StudentsServiceImp studentsService;
    @Autowired
    private CoursesService coursesServiceImp;


    @GetMapping
    public ResponseEntity<?> getAllStudent(){
        List<StudentResponse> studentResponseList = studentsService.getAllStudents();
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(studentResponseList);
        baseResponse.setCode(200);

        if (studentResponseList.isEmpty()){
            baseResponse.setMessage("No students found");
        } else {
            baseResponse.setMessage("ok");
        }
        return ResponseEntity.ok().body(baseResponse);
    }
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable int id){
        StudentResponse studentResponse = studentsService.getStudentById(id);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(studentResponse);

        if (studentResponse != null){
            baseResponse.setCode(200);
            baseResponse.setMessage("ok");
        }else {
            baseResponse.setCode(404);
            baseResponse.setMessage("No student found with id " + id);
        }
        return ResponseEntity.ok().body(baseResponse);
    }

    @PostMapping
    public ResponseEntity<?> saveStudents(@RequestBody SaveStudentRequest saveStudentRequest) {
        SaveStudentsResponse saveStudentsResponse = studentsService.saveStudents(saveStudentRequest);
        BaseResponse baseResponse = new BaseResponse();

        if (saveStudentsResponse != null){
            baseResponse.setCode(200);
            baseResponse.setMessage("ok");
            baseResponse.setData(saveStudentsResponse);
        }
        return ResponseEntity.ok().body(baseResponse);
    }

    @PostMapping("/{idStudent}/courses/{courseId}")
    public ResponseEntity<?> registerCourse(@PathVariable int idStudent, @PathVariable int courseId){
        EnrollStudentResponse enrollStudentResponse = studentsService.registerCourse(idStudent, courseId);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(enrollStudentResponse);

        CourseResponse courses = coursesServiceImp.getCoursesById(courseId);
        if (courses == null){
            baseResponse.setCode(404);
            baseResponse.setMessage("Course Not Found");
            return  ResponseEntity.ok().body(baseResponse);
        }

        StudentResponse students = studentsService.getStudentById(idStudent);
        if (students == null){
            baseResponse.setCode(404);
            baseResponse.setMessage("Student Not Found");
            return  ResponseEntity.ok().body(baseResponse);
        }

        if (enrollStudentResponse != null){
            baseResponse.setCode(200);
            baseResponse.setMessage("ok");
        }else{
            baseResponse.setCode(404);
            baseResponse.setMessage("No student found with id " + idStudent);
        }

        return  ResponseEntity.ok().body(baseResponse);
    }

    @GetMapping("/{idStudent}/courses")
    public ResponseEntity<?> getEnrollmentCoursesByIdStudent(@PathVariable int idStudent){
        BaseResponse baseResponse = new BaseResponse();

        StudentResponse students = studentsService.getStudentById(idStudent);
        if (students == null){
            baseResponse.setCode(404);
            baseResponse.setMessage("Student Not Found");
            return  ResponseEntity.ok().body(baseResponse);
        }

        EntrollCoursesByStudentsRequest enrollmentCoursesByStudentId = studentsService.getEnrollmentCoursesByStudentId(idStudent);
        baseResponse.setData(enrollmentCoursesByStudentId);

        if (enrollmentCoursesByStudentId != null){
            baseResponse.setCode(200);
            baseResponse.setMessage("ok");
        } else{
            baseResponse.setCode(404);
            baseResponse.setMessage("error");
        }
        return ResponseEntity.ok().body(baseResponse);
    }

}
