package com.bank.onboarding.onboarding.domain.model.entity;

import com.bank.onboarding.onboarding.domain.model.valueobject.AccountId;
import com.bank.onboarding.onboarding.domain.model.valueobject.AccountNumber;
import com.bank.onboarding.onboarding.domain.model.valueobject.AccountStatus;
import com.bank.onboarding.onboarding.domain.model.valueobject.CustomerId;
import com.bank.onboarding.onboarding.domain.service.AccountNumberGenerator;
import java.util.Objects;

public class Account {
	private final AccountId id;
	private final CustomerId customerId;
	private final AccountNumber accountNumber;
	private final AccountStatus status;

	public Account(AccountId id, CustomerId customerId, AccountNumber accountNumber, AccountStatus status) {
		this.id = Objects.requireNonNull(id, "id");
		this.customerId = Objects.requireNonNull(customerId, "customerId");
		this.accountNumber = Objects.requireNonNull(accountNumber, "accountNumber");
		this.status = Objects.requireNonNull(status, "status");
	}

	public static Account createNew(CustomerId customerId, AccountNumberGenerator generator) {
		Objects.requireNonNull(generator, "generator");
		return new Account(AccountId.newId(), customerId, generator.nextAccountNumber(), AccountStatus.ACTIVE);
	}

	public AccountId getId() {
		return id;
	}

	public CustomerId getCustomerId() {
		return customerId;
	}

	public AccountNumber getAccountNumber() {
		return accountNumber;
	}

	public AccountStatus getStatus() {
		return status;
	}
}
