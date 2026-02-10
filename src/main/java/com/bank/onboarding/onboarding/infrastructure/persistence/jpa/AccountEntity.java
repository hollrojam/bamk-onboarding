package com.bank.onboarding.onboarding.infrastructure.persistence.jpa;

import com.bank.onboarding.onboarding.domain.model.valueobject.AccountStatus;
import com.bank.onboarding.onboarding.domain.model.valueobject.AccountType;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "accounts")
public class AccountEntity {
	@Id
	private String id;

	@Column(nullable = false)
	private String customerId;

	@Column(nullable = false, unique = true)
	private String accountNumber;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private AccountType type;

	@Column(nullable = false)
	private BigDecimal balance;

	@Column(nullable = false)
	private OffsetDateTime openedAt;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private AccountStatus status;

	public AccountEntity() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public OffsetDateTime getOpenedAt() {
		return openedAt;
	}

	public void setOpenedAt(OffsetDateTime openedAt) {
		this.openedAt = openedAt;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}
}
