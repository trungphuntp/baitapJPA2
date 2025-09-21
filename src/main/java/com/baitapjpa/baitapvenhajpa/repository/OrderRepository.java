package com.baitapjpa.baitapvenhajpa.repository;

import com.baitapjpa.baitapvenhajpa.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {

    @Query(value = "SELECT o FROM orders o WHERE o.createdAt BETWEEN :startDate AND :endDate")
    List<Orders> findOrdersBetweenDates(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    @Query(value = "SELECT o FROM orders o ORDER BY o.totalAmount DESC LIMIT 5")
    List<Orders> findTop5ByTotalAmount();
}
