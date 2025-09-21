package com.baitapjpa.baitapvenhajpa.controller;

import com.baitapjpa.baitapvenhajpa.entity.Courses;
import com.baitapjpa.baitapvenhajpa.request.SaveCoursesRequest;
import com.baitapjpa.baitapvenhajpa.response.BaseResponse;
import com.baitapjpa.baitapvenhajpa.response.CourseResponse;
import com.baitapjpa.baitapvenhajpa.service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CoursesController {
    @Autowired
    private CoursesService coursesService;

    @GetMapping
    public ResponseEntity<?> getAllCourses() {
        List<CourseResponse> courseResponseList = coursesService.getAllCourses();
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(courseResponseList);
        if (courseResponseList.isEmpty()) {
            baseResponse.setCode(404);
            baseResponse.setMessage("Courses Not Found");
        }else{
            baseResponse.setCode(200);
            baseResponse.setMessage("ok");
        }
        return ResponseEntity.ok().body(baseResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCoursesById(@PathVariable int id) {
        CourseResponse courses = coursesService.getCoursesById(id);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(courses);
        if (courses != null) {
            baseResponse.setCode(200);
            baseResponse.setMessage("ok");
        } else{
            baseResponse.setCode(404);
            baseResponse.setMessage("Courses Not Found");
        }
        return ResponseEntity.ok().body(baseResponse);
    }

    @PostMapping
    public ResponseEntity<?> saveCourses(@RequestBody SaveCoursesRequest saveCoursesRequest) {
        Courses courses = coursesService.saveCourses(saveCoursesRequest);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(courses);
        if (courses != null) {
            baseResponse.setCode(200);
            baseResponse.setMessage("ok");
        }else{
            baseResponse.setCode(404);
            baseResponse.setMessage("Courses Not Found");
        }
        return ResponseEntity.ok().body(baseResponse);
    }
}
