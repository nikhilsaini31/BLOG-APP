package com.blogApp.controllers;

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

import com.blogApp.entity.Category;
import com.blogApp.payload.CategoryDto;
import com.blogApp.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/create")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		
		CategoryDto category = this.categoryService.createCategory(categoryDto);
		
		return  ResponseEntity.status(HttpStatus.CREATED).body(category);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategories(){
		
		List<CategoryDto> allCategories = this.categoryService.getAllCategories();
		
		return ResponseEntity.status(HttpStatus.FOUND).body(allCategories);
	}
	
	@GetMapping("get/{id}")
	public ResponseEntity<CategoryDto> getSingleCaregory(@PathVariable("id") Integer categoryId){
		
		CategoryDto categoryDto = this.categoryService.getSingleCategory(categoryId);
		
		return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.FOUND);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable("id") Integer categoryId ){
		
		CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto, categoryId);
		
		 return ResponseEntity.status(HttpStatus.OK).body(updateCategory);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity deleteCategory(@PathVariable("id") Integer categoryId) {
		
		this.categoryService.deleteCategory(categoryId);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Category deleted successfully");
	}
}
