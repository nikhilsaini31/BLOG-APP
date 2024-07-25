package com.blogApp.services.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogApp.entity.Category;
import com.blogApp.exceptions.ResourceNotFoundException;
import com.blogApp.payload.CategoryDto;
import com.blogApp.repositories.CategoryRepo;
import com.blogApp.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		Category category = this.dtoToCategory(categoryDto);
		
		Category saveCategory = this.categoryRepo.save(category);
		
		return this.categoryToDto(saveCategory); 
	}

	@Override
	public CategoryDto getSingleCategory(Integer categoryId) {
	
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category", "Id", categoryId));
		
		return this.categoryToDto(category);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		
		List<Category> categories = this.categoryRepo.findAll();
		
		List<CategoryDto> CategoryDtos = categories.stream().map(category->this.categoryToDto(category)).collect(Collectors.toList());
		
		return CategoryDtos;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category", "Id", categoryId));
		
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		Category saveCategory = this.categoryRepo.save(category);
		
		return this.categoryToDto(saveCategory);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category", "Id", categoryId));
		
		this.categoryRepo.delete(category);
	}
	
	
	private Category dtoToCategory(CategoryDto categoryDto) {
		
		Category category = this.modelMapper.map(categoryDto, Category.class);
		
		return category; 
	}
	
	
	private CategoryDto categoryToDto(Category category) {
		
     CategoryDto categoryDto = this.modelMapper.map(category, CategoryDto.class);
		
		return categoryDto;
	}

}
