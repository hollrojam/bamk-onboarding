package com.bank.onboarding.onboarding.application.usecase;

import com.bank.onboarding.onboarding.application.dto.AccountResponse;
import com.bank.onboarding.onboarding.domain.exception.AccountNotFoundException;
import com.bank.onboarding.onboarding.domain.model.entity.Account;
import com.bank.onboarding.onboarding.domain.model.valueobject.AccountNumber;
import com.bank.onboarding.onboarding.domain.model.valueobject.AccountStatus;
import com.bank.onboarding.onboarding.domain.port.repository.AccountRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateAccountStatusUseCase {
	private final AccountRepository accountRepository;

	public AccountResponse execute(String accountNumberValue, AccountStatus status) {
		AccountNumber accountNumber = AccountNumber.of(accountNumberValue);
		Account account = accountRepository.findByAccountNumber(accountNumber)
				.orElseThrow(() -> new AccountNotFoundException("Cuenta no encontrada"));
		Account updated = account.withStatus(status);
		Account saved = accountRepository.save(updated);
		return new AccountResponse(
				saved.getAccountNumber().value(),
				saved.getType().name(),
				saved.getBalance().toPlainString(),
				saved.getOpenedAt().toString(),
				saved.getStatus().name()
		);
	}
}
