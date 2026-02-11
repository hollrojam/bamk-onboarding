package com.bank.onboarding.onboarding.interfaces.exception;

import com.bank.onboarding.onboarding.domain.exception.CustomerNotFoundException;
import com.bank.onboarding.onboarding.domain.exception.DuplicateAccountNumberException;
import com.bank.onboarding.onboarding.domain.exception.DuplicateCustomerException;
import com.bank.onboarding.onboarding.interfaces.rest.dto.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(DuplicateCustomerException.class)
	public ResponseEntity<ApiResponse<Void>> handleDuplicateCustomer(DuplicateCustomerException ex, HttpServletRequest request) {
		log.error("{} {}", request.getMethod(), request.getRequestURI(), ex);
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(ApiResponse.error(ex.getMessage(), null, "DUPLICATE_CUSTOMER"));
	}

	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ApiResponse<Void>> handleCustomerNotFound(CustomerNotFoundException ex, HttpServletRequest request) {
		log.error("{} {}", request.getMethod(), request.getRequestURI(), ex);
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(ApiResponse.error(ex.getMessage(), null, "CUSTOMER_NOT_FOUND"));
	}

	@ExceptionHandler(DuplicateAccountNumberException.class)
	public ResponseEntity<ApiResponse<Void>> handleDuplicateAccount(DuplicateAccountNumberException ex, HttpServletRequest request) {
		log.error("{} {}", request.getMethod(), request.getRequestURI(), ex);
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(ApiResponse.error(ex.getMessage(), null, "DUPLICATE_ACCOUNT_NUMBER"));
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ApiResponse<Void>> handleIllegalArgument(IllegalArgumentException ex, HttpServletRequest request) {
		log.error("{} {}", request.getMethod(), request.getRequestURI(), ex);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(ApiResponse.error(ex.getMessage(), null, "INVALID_ARGUMENT"));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<List<FieldError>>> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
		log.error("{} {}", request.getMethod(), request.getRequestURI(), ex);
		FieldError fieldError = ex.getBindingResult().getFieldErrors().stream().findFirst().orElse(null);
		String message = fieldError == null
				? "Faltan campos obligatorios"
				: "Falta o es inválido el campo: " + fieldError.getField();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(ApiResponse.error(message, ex.getBindingResult().getFieldErrors(), "VALIDATION_ERROR"));
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ApiResponse<Void>> handleMissingParam(MissingServletRequestParameterException ex, HttpServletRequest request) {
		log.error("{} {}", request.getMethod(), request.getRequestURI(), ex);
		String message = "Falta el parámetro: " + ex.getParameterName();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(ApiResponse.error(message, null, "MISSING_PARAMETER"));
	}

	@ExceptionHandler(org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ApiResponse<Void>> handleTypeMismatch(org.springframework.web.method.annotation.MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
		log.error("{} {}", request.getMethod(), request.getRequestURI(), ex);
		String message = "Tipo inválido para el parámetro: " + ex.getName();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(ApiResponse.error(message, null, "TYPE_MISMATCH"));
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ApiResponse<Void>> handleNotReadable(HttpMessageNotReadableException ex, HttpServletRequest request) {
		log.error("{} {}", request.getMethod(), request.getRequestURI(), ex);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(ApiResponse.error("Solicitud inválida", null, "INVALID_BODY"));
	}
	}

