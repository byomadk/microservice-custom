package com.example.custom.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExceptionController {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidException(MethodArgumentNotValidException e) {
		Map<String, Object> mapError = new HashMap<>();
		List<String> errors = new ArrayList<String>();
		e.getBindingResult().getFieldErrors().forEach(err -> {
			errors.add(err.getDefaultMessage());
		});
		mapError.put("msg", errors);
		return new ResponseEntity<>(mapError, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoResultException.class)
	public ResponseEntity<?> handleNoResultException(NoResultException e){
		Map<String, String> mapError = new HashMap<String, String>();
		mapError.put("msg", e.getMessage());
		return new ResponseEntity<>(mapError, HttpStatus.BAD_REQUEST);	
	}
}
