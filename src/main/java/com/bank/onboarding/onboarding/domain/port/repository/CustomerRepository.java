package com.bank.onboarding.onboarding.domain.port.repository;

import com.bank.onboarding.onboarding.domain.model.aggregate.Customer;
import com.bank.onboarding.onboarding.domain.model.valueobject.CustomerId;
import com.bank.onboarding.onboarding.domain.model.valueobject.DocumentNumber;
import com.bank.onboarding.onboarding.domain.model.valueobject.Email;
import java.util.Optional;

public interface CustomerRepository {
	Customer save(Customer customer);
	Optional<Customer> findById(CustomerId id);
	Optional<Customer> findByDocumentNumber(DocumentNumber documentNumber);
	Optional<Customer> findByEmail(Email email);
}
