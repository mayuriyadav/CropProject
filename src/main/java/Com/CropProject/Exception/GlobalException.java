package Com.CropProject.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import Com.CropProject.Response.ApiResponse;

@RestControllerAdvice

public class GlobalException {
 @ExceptionHandler(ResourceNotFoundException.class)
 public ResponseEntity<ApiResponse>resourceNotFoundException(ResourceNotFoundException ex){
	 
	 String message = ex.getMessage();
	 return new ResponseEntity<ApiResponse>(new ApiResponse(message,false),HttpStatus.NOT_FOUND);
 }
}
