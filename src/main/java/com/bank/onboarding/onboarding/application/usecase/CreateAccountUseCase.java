package com.bank.onboarding.onboarding.application.usecase;

import com.bank.onboarding.onboarding.application.dto.AccountRequest;
import com.bank.onboarding.onboarding.application.dto.AccountResponse;
import com.bank.onboarding.onboarding.domain.exception.DuplicateAccountNumberException;
import com.bank.onboarding.onboarding.domain.exception.CustomerNotFoundException;
import com.bank.onboarding.onboarding.domain.model.entity.Account;
import com.bank.onboarding.onboarding.domain.model.valueobject.CustomerId;
import com.bank.onboarding.onboarding.domain.port.repository.AccountRepository;
import com.bank.onboarding.onboarding.domain.port.repository.CustomerRepository;
import com.bank.onboarding.onboarding.domain.service.AccountNumberGenerator;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateAccountUseCase {
	private final AccountRepository accountRepository;
	private final CustomerRepository customerRepository;
	private final AccountNumberGenerator accountNumberGenerator;

	public AccountResponse execute(AccountRequest request) {
		CustomerId customerId = CustomerId.of(request.customerId());
		if (customerRepository.findById(customerId).isEmpty()) {
			throw new CustomerNotFoundException("Cliente no encontrado");
		}
		BigDecimal initialBalance = request.saldoInicial();
		if (initialBalance.compareTo(BigDecimal.valueOf(0.01)) < 0) {
			throw new IllegalArgumentException("El saldo inicial mínimo es 0.01");
		}
		Account account = Account.createNew(customerId, request.tipo(), initialBalance, accountNumberGenerator);
		if (accountRepository.findByAccountNumber(account.getAccountNumber()).isPresent()) {
			throw new DuplicateAccountNumberException("El número de cuenta ya existe");
		}
		Account saved = accountRepository.save(account);
		return toResponse(saved);
	}

	private AccountResponse toResponse(Account account) {
		return new AccountResponse(
				account.getAccountNumber().value(),
				account.getType().name(),
				account.getBalance().toPlainString(),
				account.getOpenedAt().toString(),
				account.getStatus().name()
		);
	}
}
