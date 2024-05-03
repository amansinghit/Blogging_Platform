package com.core.BloggingApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.core.BloggingApplication.payloads.ApiResponse;
import com.core.BloggingApplication.payloads.CategoryDTO;
import com.core.BloggingApplication.payloads.PostDTO;
import com.core.BloggingApplication.payloads.PostResponse;
import com.core.BloggingApplication.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

@Autowired
private PostService postservice;

    @PostMapping("/user/{UserId}/category/{CategoryId}/posts")
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postdto,@PathVariable("UserId") Integer UserId,@PathVariable("CategoryId") Integer CategoryId)
	{
		PostDTO PostAdded=this.postservice.createPost(postdto, UserId, CategoryId);
		return new ResponseEntity<PostDTO>(PostAdded,HttpStatus.CREATED);
	}
	//get posts  by user
    @GetMapping("/user/{UserId}")
	public ResponseEntity<List<PostDTO>> getPostByUserId(@PathVariable("UserId") Integer UserId)
	{
		List<PostDTO> Posts=this.postservice.getPostByUser(UserId);
		return new ResponseEntity<List<PostDTO>>(Posts,HttpStatus.OK);
	}
    @GetMapping("/Category/{CategoryId}")
   	public ResponseEntity<List<PostDTO>> getPostByCategoryId(@PathVariable("CategoryId") Integer CategoryId)
   	{
   		List<PostDTO> Posts=this.postservice.getPostByCategory(CategoryId);
   		return new ResponseEntity<List<PostDTO>>(Posts,HttpStatus.OK);
   	}
    @GetMapping("/get/{PostId}")
   	public ResponseEntity<PostDTO> getPostByPostId(@PathVariable("PostId") Integer PostId)
   	{
   		PostDTO Postdto=this.postservice.getPostById(PostId);
   		return new ResponseEntity<PostDTO>(Postdto,HttpStatus.OK);
   	}
    @GetMapping("/getAllPost")
   	public ResponseEntity<PostResponse> getAllPosts(
   			@RequestParam(value="pageNumber",defaultValue ="0",required = false) Integer pageNumber,
   			@RequestParam(value="pageSize",defaultValue = "5",required = false) Integer pageSize
   			)
   	
   	{
    	PostResponse Posts=this.postservice.getAllPosts( pageSize, pageNumber);
   		return new ResponseEntity<PostResponse>(Posts,HttpStatus.OK);
   	}
    @DeleteMapping("/deletePost/{PostId}")
   	public ResponseEntity<ApiResponse> getAllPosts(@PathVariable("PostId") Integer PostId)
   	{
   		this.postservice.deletePost(PostId);
   		return new ResponseEntity<ApiResponse>(new ApiResponse("Post Deleted Successfully",true),HttpStatus.OK);
   	}
    @PutMapping("/updatePost/{PostId}")
   	public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postdto,@PathVariable("PostId") Integer PostId)
   	{
   		PostDTO updatedpostdto=this.postservice.updatePost(postdto, PostId);
   		return new ResponseEntity<PostDTO>(updatedpostdto,HttpStatus.OK);
   	}
	
}
