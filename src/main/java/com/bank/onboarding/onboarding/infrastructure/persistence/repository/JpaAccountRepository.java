package com.bank.onboarding.onboarding.infrastructure.persistence.repository;

import com.bank.onboarding.onboarding.infrastructure.persistence.jpa.AccountEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAccountRepository extends JpaRepository<AccountEntity, String> {
	List<AccountEntity> findByCustomerId(String customerId);
	Optional<AccountEntity> findByAccountNumber(String accountNumber);
}
