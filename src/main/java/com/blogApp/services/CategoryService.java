package com.blogApp.services;

import java.util.List;

import com.blogApp.payload.CategoryDto;

public interface CategoryService {
	
	public CategoryDto createCategory(CategoryDto categoryDto);
	
	public CategoryDto getSingleCategory(Integer categoryId);
	
	public List<CategoryDto> getAllCategories();
	
	public CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
	
	public void deleteCategory(Integer categoryId);

}
