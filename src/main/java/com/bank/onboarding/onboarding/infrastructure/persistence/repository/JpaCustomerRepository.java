package com.bank.onboarding.onboarding.infrastructure.persistence.repository;

import com.bank.onboarding.onboarding.infrastructure.persistence.jpa.CustomerEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCustomerRepository extends JpaRepository<CustomerEntity, String> {
	Optional<CustomerEntity> findByDocumentNumber(String documentNumber);
	Optional<CustomerEntity> findByEmail(String email);
}
