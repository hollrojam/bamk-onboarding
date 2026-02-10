package com.bank.onboarding.onboarding.domain.model.valueobject;

import java.util.UUID;

public record CustomerId(String value) {
	public CustomerId {
		if (value == null || value.isBlank()) {
			throw new IllegalArgumentException("customerId is required");
		}
	}

	public static CustomerId newId() {
		return new CustomerId(UUID.randomUUID().toString());
	}

	public static CustomerId of(String value) {
		return new CustomerId(value);
	}
}
