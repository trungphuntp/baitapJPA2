package com.baitapjpa.baitapvenhajpa.repository;

import com.baitapjpa.baitapvenhajpa.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees,Integer> {
}
