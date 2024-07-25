package com.blogApp.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
	
	private Integer categoryId;
	
	@NotEmpty
	@Size(min = 4,max = 100)
	private String categoryTitle;
	
	@NotEmpty
	@Size(min = 10,max = 1000)
	private String categoryDescription;
	
	

}
