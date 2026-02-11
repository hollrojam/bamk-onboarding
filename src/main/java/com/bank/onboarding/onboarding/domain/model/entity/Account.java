package com.bank.onboarding.onboarding.domain.model.entity;

import com.bank.onboarding.onboarding.domain.model.valueobject.AccountId;
import com.bank.onboarding.onboarding.domain.model.valueobject.AccountNumber;
import com.bank.onboarding.onboarding.domain.model.valueobject.AccountStatus;
import com.bank.onboarding.onboarding.domain.model.valueobject.AccountType;
import com.bank.onboarding.onboarding.domain.model.valueobject.CustomerId;
import com.bank.onboarding.onboarding.domain.service.AccountNumberGenerator;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;
import lombok.Getter;

@Getter
public class Account {
	private final AccountId id;
	private final CustomerId customerId;
	private final AccountNumber accountNumber;
	private final AccountType type;
	private final BigDecimal balance;
	private final OffsetDateTime openedAt;
	private final AccountStatus status;

	public Account(AccountId id, CustomerId customerId, AccountNumber accountNumber, AccountType type, BigDecimal balance, OffsetDateTime openedAt, AccountStatus status) {
		this.id = Objects.requireNonNull(id, "id");
		this.customerId = Objects.requireNonNull(customerId, "customerId");
		this.accountNumber = Objects.requireNonNull(accountNumber, "accountNumber");
		this.type = Objects.requireNonNull(type, "type");
		this.balance = Objects.requireNonNull(balance, "balance");
		this.openedAt = Objects.requireNonNull(openedAt, "openedAt");
		this.status = Objects.requireNonNull(status, "status");
	}

	public static Account createNew(CustomerId customerId, AccountType type, BigDecimal initialBalance, AccountNumberGenerator generator) {
		Objects.requireNonNull(generator, "generator");
		return new Account(AccountId.newId(), customerId, generator.nextAccountNumber(), type, initialBalance, OffsetDateTime.now(), AccountStatus.ACTIVE);
	}

}
