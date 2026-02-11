package com.bank.onboarding.onboarding.application.dto;

public record CustomerResponse(
		String id,
		String nombre,
		String email,
		String fechaRegistro,
		String estado
) {
}
