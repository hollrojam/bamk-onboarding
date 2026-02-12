package com.bank.onboarding.onboarding.application.dto;

import com.bank.onboarding.onboarding.domain.model.valueobject.AccountStatus;
import jakarta.validation.constraints.NotNull;

public record AccountStatusRequest(
		@NotNull AccountStatus status
) {
}
