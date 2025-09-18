package com.baitapjpa.baitapvenhajpa.service;

import com.baitapjpa.baitapvenhajpa.entity.Courses;
import com.baitapjpa.baitapvenhajpa.repository.CoursesRepository;
import com.baitapjpa.baitapvenhajpa.request.SaveCoursesRequest;
import com.baitapjpa.baitapvenhajpa.response.CourseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CoursesService {
    @Autowired
    private CoursesRepository coursesRepository;

    public List<CourseResponse> getAllCourses() {
        return  coursesRepository.findAll().stream().map(c -> new CourseResponse(c.getId(),c.getName())).toList();
    }

    public Courses getCoursesById(int id) {
        return coursesRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Course not found"));
    }

    public Courses saveCourses(SaveCoursesRequest saveCoursesRequest) {
        Courses courses = new Courses();
        courses.setName(saveCoursesRequest.getName());
        return coursesRepository.save(courses);
    }
}
