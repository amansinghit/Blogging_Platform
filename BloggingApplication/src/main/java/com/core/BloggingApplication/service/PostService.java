package com.core.BloggingApplication.service;

import java.util.List;

import com.core.BloggingApplication.entity.Post;
import com.core.BloggingApplication.payloads.PostDTO;
import com.core.BloggingApplication.payloads.PostResponse;

public interface PostService {
public PostDTO createPost(PostDTO postdto,Integer userId,Integer categoryId); 
public PostDTO updatePost(PostDTO postdto,Integer id); 
public void  deletePost(Integer id); 
public  PostDTO getPostById(Integer id);
public PostResponse getAllPosts(Integer  pageNumber,Integer pageSize);
public List<PostDTO> getPostByCategory(Integer categoryId);
public List<PostDTO> getPostByUser(Integer userId);
public List<PostDTO> searchPosts(String Keyword);
}
