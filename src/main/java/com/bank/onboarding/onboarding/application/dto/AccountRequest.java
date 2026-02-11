package com.bank.onboarding.onboarding.application.dto;

import com.bank.onboarding.onboarding.domain.model.valueobject.AccountType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record AccountRequest(
		@NotBlank String customerId,
		@NotNull AccountType tipo,
		@NotNull @DecimalMin(value = "0.01") BigDecimal saldoInicial
) {
}
