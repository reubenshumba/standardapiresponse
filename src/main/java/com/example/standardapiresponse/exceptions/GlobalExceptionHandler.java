package com.example.standardapiresponse.exceptions;

import com.example.standardapiresponse.responses.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.net.UnknownHostException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex) {

		log.error(ex.getMessage(), ex);

		if (ex.getCause() instanceof UnknownHostException) {
			Response<String> error = Response.failedResponse(HttpStatus.NOT_FOUND.value(),
					ex.getLocalizedMessage());
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}
		Response<String> error = Response.failedResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"We are unable to process your request at this time, please try again later.", ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<Object> handleApplicationException(ApplicationException ex) {
		return ResponseEntity.status(ex.getHttpStatus()).body(Response.failedResponse(ex.getHttpStatus().value(), ex.getMessage()));
	}

}
