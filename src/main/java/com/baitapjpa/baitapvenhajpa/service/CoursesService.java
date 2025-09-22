package com.baitapjpa.baitapvenhajpa.service;

import com.baitapjpa.baitapvenhajpa.entity.Courses;
import com.baitapjpa.baitapvenhajpa.request.SaveCoursesRequest;
import com.baitapjpa.baitapvenhajpa.response.CourseResponse;

import java.util.List;

public interface CoursesService {
    List<CourseResponse> getAllCourses();
    CourseResponse getCoursesById(int id);
    Courses saveCourses(SaveCoursesRequest saveCoursesRequest);
}
