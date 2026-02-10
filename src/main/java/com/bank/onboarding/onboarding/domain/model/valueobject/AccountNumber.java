package com.bank.onboarding.onboarding.domain.model.valueobject;

public record AccountNumber(String value) {
	public AccountNumber {
		if (value == null || value.isBlank()) {
			throw new IllegalArgumentException("accountNumber is required");
		}
	}

	public static AccountNumber of(String value) {
		return new AccountNumber(value);
	}
}
