package com.bank.onboarding.onboarding.domain.model.valueobject;

import java.util.UUID;

public record AccountId(String value) {
	public AccountId {
		if (value == null || value.isBlank()) {
			throw new IllegalArgumentException("accountId is required");
		}
	}

	public static AccountId newId() {
		return new AccountId(UUID.randomUUID().toString());
	}

	public static AccountId of(String value) {
		return new AccountId(value);
	}
}
