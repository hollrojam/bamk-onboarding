package com.bank.onboarding.onboarding.interfaces.rest.controller;

import com.bank.onboarding.onboarding.application.dto.AccountRequest;
import com.bank.onboarding.onboarding.application.dto.AccountResponse;
import com.bank.onboarding.onboarding.application.usecase.CreateAccountUseCase;
import com.bank.onboarding.onboarding.application.usecase.GetAccountsByCustomerUseCase;
import com.bank.onboarding.onboarding.application.usecase.UpdateAccountStatusUseCase;
import com.bank.onboarding.onboarding.application.dto.AccountStatusRequest;
import com.bank.onboarding.onboarding.domain.model.valueobject.AccountStatus;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
	private final CreateAccountUseCase createAccountUseCase;
	private final GetAccountsByCustomerUseCase getAccountsByCustomerUseCase;
	private final UpdateAccountStatusUseCase updateAccountStatusUseCase;

	@PostMapping
	public ResponseEntity<ApiResponse<AccountResponse>> create(@Valid @RequestBody AccountRequest request) {
		AccountResponse response = createAccountUseCase.execute(request);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ApiResponse.success("Cuenta creada", response, "ACCOUNT_CREATED"));
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<AccountResponse>>> list(@RequestParam String customerId) {
		List<AccountResponse> response = getAccountsByCustomerUseCase.execute(customerId);
		return ResponseEntity.ok(ApiResponse.success("Cuentas listadas", response, "ACCOUNTS_LISTED"));
	}

	@PostMapping("/status")
	public ResponseEntity<ApiResponse<AccountResponse>> updateStatus(@RequestParam String accountNumber, @Valid @RequestBody AccountStatusRequest request) {
		AccountStatus status = request.status();
		AccountResponse response = updateAccountStatusUseCase.execute(accountNumber, status);
		return ResponseEntity.ok(ApiResponse.success("Estado actualizado", response, "ACCOUNT_STATUS_UPDATED"));
	}
}
