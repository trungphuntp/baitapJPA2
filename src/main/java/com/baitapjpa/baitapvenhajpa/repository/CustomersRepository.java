package com.baitapjpa.baitapvenhajpa.repository;

import com.baitapjpa.baitapvenhajpa.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepository extends JpaRepository<Customers, Integer> {

}
