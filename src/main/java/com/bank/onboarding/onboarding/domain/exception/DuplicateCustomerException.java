package com.bank.onboarding.onboarding.domain.exception;

public class DuplicateCustomerException extends RuntimeException {
	public DuplicateCustomerException(String message) {
		super(message);
	}
}
