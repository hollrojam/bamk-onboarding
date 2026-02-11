package com.bank.onboarding.onboarding.application.dto;

public record AccountResponse(
		String accountNumber,
		String tipo,
		String saldoActual,
		String fechaApertura
) {
}
