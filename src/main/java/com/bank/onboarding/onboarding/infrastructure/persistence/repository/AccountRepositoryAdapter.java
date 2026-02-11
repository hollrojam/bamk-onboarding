package com.bank.onboarding.onboarding.infrastructure.persistence.repository;

import com.bank.onboarding.onboarding.domain.model.entity.Account;
import com.bank.onboarding.onboarding.domain.model.valueobject.AccountId;
import com.bank.onboarding.onboarding.domain.model.valueobject.AccountNumber;
import com.bank.onboarding.onboarding.domain.model.valueobject.CustomerId;
import com.bank.onboarding.onboarding.domain.port.repository.AccountRepository;
import com.bank.onboarding.onboarding.infrastructure.persistence.jpa.AccountEntity;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryAdapter implements AccountRepository {
	private final JpaAccountRepository jpaAccountRepository;

	@Override
	public Account save(Account account) {
		AccountEntity saved = jpaAccountRepository.save(toEntity(account));
		return toDomain(saved);
	}

	@Override
	public List<Account> findByCustomerId(CustomerId customerId) {
		return jpaAccountRepository.findByCustomerId(customerId.value()).stream()
				.map(this::toDomain)
				.collect(Collectors.toList());
	}

	@Override
	public Optional<Account> findByAccountNumber(AccountNumber accountNumber) {
		return jpaAccountRepository.findByAccountNumber(accountNumber.value()).map(this::toDomain);
	}

	private AccountEntity toEntity(Account account) {
		AccountEntity entity = new AccountEntity();
		entity.setId(account.getId().value());
		entity.setCustomerId(account.getCustomerId().value());
		entity.setAccountNumber(account.getAccountNumber().value());
		entity.setType(account.getType());
		entity.setBalance(account.getBalance());
		entity.setOpenedAt(account.getOpenedAt());
		entity.setStatus(account.getStatus());
		return entity;
	}

	private Account toDomain(AccountEntity entity) {
		return new Account(
				AccountId.of(entity.getId()),
				CustomerId.of(entity.getCustomerId()),
				AccountNumber.of(entity.getAccountNumber()),
				entity.getType(),
				entity.getBalance(),
				entity.getOpenedAt(),
				entity.getStatus()
		);
	}
}
