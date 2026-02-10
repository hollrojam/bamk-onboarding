package com.bank.onboarding.onboarding.domain.port.repository;

import com.bank.onboarding.onboarding.domain.model.entity.Account;
import com.bank.onboarding.onboarding.domain.model.valueobject.CustomerId;
import java.util.List;

public interface AccountRepository {
	Account save(Account account);
	List<Account> findByCustomerId(CustomerId customerId);
}
