package com.core.BloggingApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.core.BloggingApplication.entity.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer> {

	
}
