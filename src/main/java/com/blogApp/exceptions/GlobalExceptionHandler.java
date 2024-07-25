package com.blogApp.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blogApp.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
	@ExceptionHandler(ResourceNotFoundException.class) // this is our custom class
	public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException ex){
		
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, false);
	    
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		
		Map<String, String> response=new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach((error)->{
			
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			response.put(fieldName, message);
		});
		
		
		return new ResponseEntity<Map<String,String>>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(StringIndexOutOfBoundsException.class)
	public ResponseEntity<ApiResponse> stringIndexOutOfBoundsException(StringIndexOutOfBoundsException ex){
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Please select a valid file", false));
	}
	
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ApiResponse> apiException(ApiException ex){
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Invalid username or password", false));
	}
	
	
}
