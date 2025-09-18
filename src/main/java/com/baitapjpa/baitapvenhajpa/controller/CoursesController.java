package com.baitapjpa.baitapvenhajpa.controller;

import com.baitapjpa.baitapvenhajpa.entity.Courses;
import com.baitapjpa.baitapvenhajpa.request.SaveCoursesRequest;
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
    public ResponseEntity<List<CourseResponse>> getAllCourses() {
        return ResponseEntity.ok().body(coursesService.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Courses> getCoursesById(@PathVariable int id) {
        return ResponseEntity.ok().body(coursesService.getCoursesById(id));
    }

    @PostMapping
    public ResponseEntity<Courses> saveCourses(@RequestBody SaveCoursesRequest saveCoursesRequest) {
        return ResponseEntity.ok().body(coursesService.saveCourses(saveCoursesRequest));
    }
}
