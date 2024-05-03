package com.core.BloggingApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.core.BloggingApplication.entity.Category;
import com.core.BloggingApplication.entity.Post;
import com.core.BloggingApplication.entity.User;

public interface PostRepo extends JpaRepository<Post, Integer>{

	 List<Post> findByUser(User user);
	 List<Post> findByCategory(Category category);
}
