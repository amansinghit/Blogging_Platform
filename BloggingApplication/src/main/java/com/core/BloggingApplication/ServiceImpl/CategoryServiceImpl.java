package com.core.BloggingApplication.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.BloggingApplication.entity.Category;
import com.core.BloggingApplication.entity.User;
import com.core.BloggingApplication.exception.ResourceNotFoundException;
import com.core.BloggingApplication.payloads.CategoryDTO;
import com.core.BloggingApplication.payloads.UserDTO;
import com.core.BloggingApplication.repository.CategoryRepo;
import com.core.BloggingApplication.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	
@Autowired
private CategoryRepo categoryRepo;
@Autowired
private ModelMapper modelmapper; 

	@Override
	public CategoryDTO create_Category(CategoryDTO categorydto) {
		Category category =this.modelmapper.map(categorydto,Category.class);
		Category saved_category=this.categoryRepo.save(category);
		return this.modelmapper.map(saved_category, CategoryDTO.class);
	}

	@Override
	public CategoryDTO update_Category(CategoryDTO categorydto, Integer id) {
	Optional<Category> category=this.categoryRepo.findById(id);
	if(category.isPresent()) {
		Category actualCategory=category.get();
		actualCategory.setCategoryDescription(categorydto.getCategoryDescription());
		actualCategory.setCategoryTitle(categorydto.getCategoryTitle());
		Category updatedCategory=this.categoryRepo.save(actualCategory);
		return this.modelmapper.map(updatedCategory,CategoryDTO.class);
	   }
		throw new  ResourceNotFoundException("Category","Category_Id",id);
	}

	@Override
	public void delete_Category(Integer id) {
		Optional<Category> category=this.categoryRepo.findById(id);
		if(category.isPresent())
		{
			this.categoryRepo.delete(category.get());
			
		}
		else
			throw new ResourceNotFoundException("Category", "Id", id);		
		
	}

	@Override
	public CategoryDTO get_Category(Integer id) {
		Optional <Category> category=this.categoryRepo.findById(id);
		if(category.isPresent()) {
			Category actualuser=category.get();
			return this.modelmapper.map(actualuser, CategoryDTO.class);
		}
		else {
			throw new ResourceNotFoundException("User","Id",id);
		}
	}

	@Override
	public List<CategoryDTO> getAll_Category() {
		List<CategoryDTO> listCategorydto=new ArrayList<>();
		
		List<Category> listCategory=this.categoryRepo.findAll();
		for(Category eachCategory:listCategory) {
//			UserDTO eachUserDTO=userToUserDTO(eachCategory);
//			listusrdto.add(eachUserDTO);
			listCategorydto.add(this.modelmapper.map(eachCategory, CategoryDTO.class));
		}
		return listCategorydto;
	}
	


}
