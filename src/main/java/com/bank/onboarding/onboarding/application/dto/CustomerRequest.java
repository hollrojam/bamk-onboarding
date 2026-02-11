package com.bank.onboarding.onboarding.application.dto;

import com.bank.onboarding.onboarding.domain.model.valueobject.DocumentType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
		@NotNull DocumentType documentType,
		@NotBlank String documentNumber,
		@NotBlank String nombre,
		@Email @NotBlank String email
) {
}
