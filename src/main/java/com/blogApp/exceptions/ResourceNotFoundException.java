package com.blogApp.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {

	 String resourceName;
	 String fieldName;
	 long   fieldValue;
	 
	public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue)
	{            //(first %s=resourceName,second %s=fieldName and %d= fieldValue)
		super(String.format("%s not fount with %s : %d", resourceName,fieldName,fieldValue));
		         //output -(user not found with id : 111) (if resourceName=user,fieldName=id,fieldValue=111)   
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	/*let 
	 * String resourceName = "User"; 
	 *  String fieldName = "ID"; 
	 *  long fieldValue =12345L;
	 *  
	 *   String formattedString = String.format("%s not found with %s : %d", resourceName, fieldName, fieldValue); 
	 *   
	 *   System.out.println(formattedString);
	 */
	
	/*
	 * %s: This will be replaced by the value of resourceName, which is "User". 
	 * %s: This will be replaced by the value of fieldName, which is "ID".
	 * %d: This will be replaced by the value of fieldValue, which is 12345L.
	 */
	  
	/*
	 * output is 
	 * User not found with ID : 12345
	 */
	
}
