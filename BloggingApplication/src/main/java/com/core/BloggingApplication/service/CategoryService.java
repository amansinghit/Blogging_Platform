package com.core.BloggingApplication.service;

import java.util.List;

import com.core.BloggingApplication.payloads.CategoryDTO;

public interface CategoryService {

	public CategoryDTO create_Category(CategoryDTO categorydto);
	public CategoryDTO update_Category(CategoryDTO categorydto,Integer id);
	public void delete_Category(Integer id);
	public CategoryDTO get_Category(Integer id);
	public List<CategoryDTO> getAll_Category();
	
}
