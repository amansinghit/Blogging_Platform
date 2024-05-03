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
import org.springframework.web.bind.annotation.RestController;

import com.core.BloggingApplication.payloads.ApiResponse;
import com.core.BloggingApplication.payloads.CategoryDTO;
import com.core.BloggingApplication.payloads.UserDTO;
import com.core.BloggingApplication.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
@Autowired
private CategoryService categoryservice;

@PostMapping("/add_category")
public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO ) 
{
	CategoryDTO Categoryadded=this.categoryservice.create_Category(categoryDTO);
	return new ResponseEntity<CategoryDTO>(Categoryadded,HttpStatus.OK);
	
}

@PutMapping("/update_category/{Id}")
public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO,@PathVariable("Id")  Integer id) 
{
	CategoryDTO Categoryadded=this.categoryservice.update_Category(categoryDTO,id);
	return new ResponseEntity<CategoryDTO>(Categoryadded,HttpStatus.OK);
	
}
@DeleteMapping("/delete_category/{Id}")
public ResponseEntity<ApiResponse> deleteCategory(@Valid @PathVariable("Id")  Integer id) 
{
	this.categoryservice.delete_Category(id);
	return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleteed successfully",true),HttpStatus.OK);
	
}


@GetMapping("/get_category/{Id}")
public ResponseEntity<CategoryDTO> getCategoryByid(@Valid @PathVariable("Id")  Integer id) 
{
	CategoryDTO Categoryadded=this.categoryservice.get_Category(id);
	return new ResponseEntity<CategoryDTO>(Categoryadded,HttpStatus.OK);
	
}

@GetMapping("/findAllCategory")
public ResponseEntity<List<CategoryDTO>> getAllCategory(){
	List<CategoryDTO> categoeydto=this.categoryservice.getAll_Category();
	return  ResponseEntity.ok(categoeydto);
}	

}
