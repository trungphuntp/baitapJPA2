package com.baitapjpa.baitapvenhajpa.service.Imp;

import com.baitapjpa.baitapvenhajpa.entity.Courses;
import com.baitapjpa.baitapvenhajpa.repository.CoursesRepository;
import com.baitapjpa.baitapvenhajpa.request.SaveCoursesRequest;
import com.baitapjpa.baitapvenhajpa.response.CourseResponse;
import com.baitapjpa.baitapvenhajpa.service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursesServiceImp implements CoursesService {
    @Autowired
    private CoursesRepository coursesRepository;

    public List<CourseResponse> getAllCourses() {
        return  coursesRepository.findAll().stream().map(c -> new CourseResponse(c.getId(),c.getName())).toList();
    }

    public CourseResponse getCoursesById(int id) {
        Courses courses = coursesRepository.findById(id).orElse(null);
        CourseResponse  courseResponse = new CourseResponse();
        if (courses != null){
            courseResponse.setId(courses.getId());
            courseResponse.setName(courses.getName());
        }else {
            return null;
        }
        return courseResponse;
    }

    public Courses saveCourses(SaveCoursesRequest saveCoursesRequest) {
        Courses courses = new Courses();
        courses.setName(saveCoursesRequest.getName());
        return coursesRepository.save(courses);
    }
}
