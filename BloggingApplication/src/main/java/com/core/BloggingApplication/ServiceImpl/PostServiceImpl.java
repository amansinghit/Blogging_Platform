package com.core.BloggingApplication.ServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.query.Page;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.core.BloggingApplication.entity.Category;
import com.core.BloggingApplication.entity.Post;
import com.core.BloggingApplication.entity.User;
import com.core.BloggingApplication.exception.ResourceNotFoundException;
import com.core.BloggingApplication.payloads.PostDTO;
import com.core.BloggingApplication.payloads.PostResponse;
import com.core.BloggingApplication.repository.CategoryRepo;
import com.core.BloggingApplication.repository.PostRepo;
import com.core.BloggingApplication.repository.UserRepo;
import com.core.BloggingApplication.service.PostService;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
	private ModelMapper modelmapper;
    @Autowired
    private PostRepo postrepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo  categoryRepo;
    
	@Override
	public PostDTO createPost(PostDTO postdto,Integer userId,Integer caregoryId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
		Category category=this.categoryRepo.findById(caregoryId).orElseThrow(()->new ResourceNotFoundException("Category","Id",caregoryId));
		
		Post post=this.modelmapper.map(postdto, Post.class);
		post.setImageName("Default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post newPost=this.postrepo.save(post);
		return this.modelmapper.map(newPost, PostDTO.class);
		
	}

	@Override
	public PostDTO updatePost(PostDTO postdto, Integer id) {
		Post post=this.postrepo.findById(id).orElseThrow(()->new ResourceNotFoundException("PostId","Id",id));
		post.setContent(postdto.getContent());
		post.setTitle(postdto.getTitle());
		post.setImageName(postdto.getImageName());
	Post savedPost=this.postrepo.save(post);
		return this.modelmapper.map(savedPost, PostDTO.class);
	}

	@Override
	public void deletePost(Integer id) {
		Post post=this.postrepo.findById(id).orElseThrow(()->new ResourceNotFoundException("PostId","Id",id));
		this.postrepo.delete(post);
		
	}

	@Override
	public PostDTO getPostById(Integer id) {
		Post post=this.postrepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","Id",id));
		return this.modelmapper.map(post, PostDTO.class);
	}

	@Override
	public PostResponse getAllPosts( Integer pageNumber ,Integer pageSize) {
		
		
		
		org.springframework.data.domain.Pageable p=PageRequest.of(pageNumber, pageSize);
		
		
		org.springframework.data.domain.Page<Post> postss=this.postrepo.findAll(p);
		List<Post> posts=postss.getContent();
		List<PostDTO> listOfPostDto=new ArrayList<>();
			for(Post eachPost:posts) {
				listOfPostDto.add(this.modelmapper.map(eachPost, PostDTO.class));
			}	
			PostResponse postresponse=new PostResponse();
			postresponse.setContent(listOfPostDto);
			postresponse.setPageNumber(postss.getNumber());
			postresponse.setPageSize(postss.getSize());
			postresponse.setTotalElements(postss.getTotalElements());
			postresponse.setTotalPages(postss.getTotalPages());
			postresponse.setLastPage(postss.isLast());
		return postresponse;
	}

	@Override
	public List<PostDTO> getPostByCategory(Integer categoryId) {
	Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));
	  List<Post> posts =this.postrepo.findByCategory(category);//method given by repo by default
		List<PostDTO> postsdto=new ArrayList<>();
	  for(Post pd:posts)
		{
		  postsdto.add(this.modelmapper.map(pd, PostDTO.class));
		}
	return postsdto;
	
	}

	@Override
	public List<PostDTO> getPostByUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",userId));
		  List<Post> posts =this.postrepo.findByUser(user);//method given by repo by default
			List<PostDTO> postsdto=new ArrayList<>();
		  for(Post pd:posts)
			{
			  postsdto.add(this.modelmapper.map(pd, PostDTO.class));
			}
		return postsdto;
	}

	@Override
	public List<PostDTO> searchPosts(String Keyword){
		
		return null;
	}
	

}
