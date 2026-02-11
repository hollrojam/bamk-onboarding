package com.bank.onboarding.onboarding.interfaces.rest.controller;

import com.bank.onboarding.onboarding.application.dto.CustomerRequest;
import com.bank.onboarding.onboarding.application.dto.CustomerResponse;
import com.bank.onboarding.onboarding.application.usecase.CreateCustomerUseCase;
import com.bank.onboarding.onboarding.application.usecase.GetAllCustomersUseCase;
import com.bank.onboarding.onboarding.interfaces.rest.dto.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
	private final CreateCustomerUseCase createCustomerUseCase;
	private final GetAllCustomersUseCase getAllCustomersUseCase;

	@PostMapping
	public ResponseEntity<ApiResponse<CustomerResponse>> create(@Valid @RequestBody CustomerRequest request) {
		CustomerResponse response = createCustomerUseCase.execute(request);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ApiResponse.success("Cliente creado", response, "CUSTOMER_CREATED"));
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<CustomerResponse>>> list() {
		List<CustomerResponse> response = getAllCustomersUseCase.execute();
		return ResponseEntity.ok(ApiResponse.success("Clientes listados", response, "CUSTOMERS_LISTED"));
	}
}
