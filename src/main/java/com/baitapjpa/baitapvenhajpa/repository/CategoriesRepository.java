package com.baitapjpa.baitapvenhajpa.repository;

import com.baitapjpa.baitapvenhajpa.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories,Integer> {

}
