package com.bank.onboarding.onboarding.domain.exception;

public class DuplicateAccountNumberException extends RuntimeException {
	public DuplicateAccountNumberException(String message) {
		super(message);
	}
}
