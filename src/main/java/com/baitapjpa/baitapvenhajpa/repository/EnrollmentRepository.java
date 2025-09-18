package com.baitapjpa.baitapvenhajpa.repository;

import com.baitapjpa.baitapvenhajpa.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment,Integer> {
    Optional<Enrollment> findByStudentsIdAndCoursesId(int studentId, int courseId);
    Optional<List<Enrollment>> findByStudentsId(int  studentId);
}
