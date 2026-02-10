package com.bank.onboarding.onboarding.domain.model.valueobject;

import java.util.regex.Pattern;

public record Email(String value) {
	private static final Pattern PATTERN = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");

	public Email {
		if (value == null || value.isBlank()) {
			throw new IllegalArgumentException("email is required");
		}
		if (!PATTERN.matcher(value).matches()) {
			throw new IllegalArgumentException("email is invalid");
		}
	}

	public static Email of(String value) {
		return new Email(value);
	}
}
