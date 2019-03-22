package edu.mum.controller;

import java.util.List;

import edu.mum.domain.BlockException;
import edu.mum.domain.dao.DomainError;
import edu.mum.domain.dao.DomainErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
@ControllerAdvice
public class ExceptionController {


	@Autowired
	MessageSourceAccessor messageAccessor;
	
     @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
       public DomainErrors handleException(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
   
        DomainErrors errors = new DomainErrors();
        errors.setErrorType("ValidationError");
        for (FieldError fieldError : fieldErrors) {
         	DomainError error = new DomainError( messageAccessor.getMessage(fieldError));
                       errors.addError(error);
        }
         
         return errors;
    }
     
  	@ExceptionHandler(BlockException.class)
  	@ResponseStatus(HttpStatus.BAD_REQUEST)
 	public @ResponseBody DomainErrors handleError( BlockException exception) {

        DomainErrors errors = new DomainErrors();
        errors.setErrorType("EmployeeException");

  			errors.setMessage( exception.getFullMessage() );
 		 return errors;
 	}
  

  
}