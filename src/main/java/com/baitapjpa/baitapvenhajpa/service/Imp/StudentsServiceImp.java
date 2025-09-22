package com.baitapjpa.baitapvenhajpa.service.Imp;

import com.baitapjpa.baitapvenhajpa.entity.Courses;
import com.baitapjpa.baitapvenhajpa.entity.Enrollment;
import com.baitapjpa.baitapvenhajpa.entity.Students;
import com.baitapjpa.baitapvenhajpa.repository.CoursesRepository;
import com.baitapjpa.baitapvenhajpa.repository.EnrollmentRepository;
import com.baitapjpa.baitapvenhajpa.repository.StudentsRepository;
import com.baitapjpa.baitapvenhajpa.response.*;
import com.baitapjpa.baitapvenhajpa.request.SaveStudentRequest;
import com.baitapjpa.baitapvenhajpa.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentsServiceImp  implements StudentsService {
    @Autowired
    private StudentsRepository studentsRepository;
    @Autowired
    private CoursesRepository coursesRepository;
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public List<StudentResponse> getAllStudents()  {
        return studentsRepository.findAll().stream().map(s -> new StudentResponse(s.getId(),s.getName(),s.getEmail())).toList();
    }

    public StudentResponse getStudentById(int id){
        Students students = studentsRepository.findById(id).orElse(null);
        StudentResponse  studentResponse = new StudentResponse();
        if (students!=null){
            studentResponse.setId(students.getId());
            studentResponse.setName(students.getName());
            studentResponse.setEmail(students.getEmail());
        }else {
            studentResponse = null;
        }
        return studentResponse;
    }


    public SaveStudentsResponse saveStudents(SaveStudentRequest saveStudentRequest) {
        Students students = new Students();
        students.setName(saveStudentRequest.getName());
        students.setEmail(saveStudentRequest.getEmail());
        Students studentCreated = studentsRepository.save(students);

        SaveStudentsResponse saveStudentsResponse = new SaveStudentsResponse();
        saveStudentsResponse.setId(studentCreated.getId());
        saveStudentsResponse.setName(studentCreated.getName());
        saveStudentsResponse.setEmail(studentCreated.getEmail());

        return saveStudentsResponse;
    }


    public EnrollStudentResponse registerCourse(int idStudent, int idCourse) {
        Students students = studentsRepository.findById(idStudent)
                .orElse(null);
        Courses courses = coursesRepository.findById(idCourse)
                .orElse(null);

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
        List<Enrollment> listEnrollmentByIdStudent = enrollmentRepository.findByStudentsId(idStudent).orElse(new ArrayList<>());
        EntrollCoursesByStudentsRequest result = new EntrollCoursesByStudentsRequest();

        StudentResponse students = getStudentById(idStudent);
        if (students!=null){
            result.setId(students.getId());
            result.setName(students.getName());
            result.setEmail(students.getEmail());
        }

        List<CourseResponse> listCourses = new ArrayList<>();
        if (listEnrollmentByIdStudent.size() > 0) {
            for (Enrollment enrollment : listEnrollmentByIdStudent) {

                CourseResponse courseResponse = new CourseResponse();
                courseResponse.setId(enrollment.getCourses().getId());
                courseResponse.setName(enrollment.getCourses().getName());

                listCourses.add(courseResponse);
            }
        }
        result.setListCourses(listCourses);

        return result;
    }
}
