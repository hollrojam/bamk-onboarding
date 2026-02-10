package com.bank.onboarding.onboarding.domain.service;

import com.bank.onboarding.onboarding.domain.model.valueobject.AccountNumber;

public interface AccountNumberGenerator {
	AccountNumber nextAccountNumber();
}
