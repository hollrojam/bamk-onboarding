package com.bank.onboarding.onboarding.domain.model.valueobject;

public record DocumentNumber(String value) {
	public DocumentNumber {
		if (value == null || value.isBlank()) {
			throw new IllegalArgumentException("documentNumber is required");
		}
	}

	public static DocumentNumber of(String value) {
		return new DocumentNumber(value);
	}
}
