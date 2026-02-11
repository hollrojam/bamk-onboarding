package com.bank.onboarding.onboarding.application.usecase;

import com.bank.onboarding.onboarding.application.dto.AccountResponse;
import com.bank.onboarding.onboarding.domain.exception.CustomerNotFoundException;
import com.bank.onboarding.onboarding.domain.model.entity.Account;
import com.bank.onboarding.onboarding.domain.model.valueobject.CustomerId;
import com.bank.onboarding.onboarding.domain.port.repository.AccountRepository;
import com.bank.onboarding.onboarding.domain.port.repository.CustomerRepository;
import java.util.List;
import java.util.stream.Collectors;

public class GetAccountsByCustomerUseCase {
	private final AccountRepository accountRepository;
	private final CustomerRepository customerRepository;

	public GetAccountsByCustomerUseCase(AccountRepository accountRepository, CustomerRepository customerRepository) {
		this.accountRepository = accountRepository;
		this.customerRepository = customerRepository;
	}

	public List<AccountResponse> execute(String customerIdValue) {
		CustomerId customerId = CustomerId.of(customerIdValue);
		if (customerRepository.findById(customerId).isEmpty()) {
			throw new CustomerNotFoundException("Cliente no encontrado");
		}
		return accountRepository.findByCustomerId(customerId).stream()
				.map(this::toResponse)
				.collect(Collectors.toList());
	}

	private AccountResponse toResponse(Account account) {
		return new AccountResponse(
				account.getAccountNumber().value(),
				account.getType().name(),
				account.getBalance().toPlainString(),
				account.getOpenedAt().toString()
		);
	}
}
