package com.bank.onboarding.onboarding.infrastructure.persistence.repository;

import com.bank.onboarding.onboarding.domain.model.aggregate.Customer;
import com.bank.onboarding.onboarding.domain.model.valueobject.CustomerId;
import com.bank.onboarding.onboarding.domain.model.valueobject.DocumentNumber;
import com.bank.onboarding.onboarding.domain.model.valueobject.Email;
import com.bank.onboarding.onboarding.domain.port.repository.CustomerRepository;
import com.bank.onboarding.onboarding.infrastructure.persistence.jpa.CustomerEntity;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryAdapter implements CustomerRepository {
	private final JpaCustomerRepository jpaCustomerRepository;

	@Override
	public Customer save(Customer customer) {
		CustomerEntity saved = jpaCustomerRepository.save(toEntity(customer));
		return toDomain(saved);
	}

	@Override
	public Optional<Customer> findById(CustomerId id) {
		return jpaCustomerRepository.findById(id.value()).map(this::toDomain);
	}

	@Override
	public Optional<Customer> findByDocumentNumber(DocumentNumber documentNumber) {
		return jpaCustomerRepository.findByDocumentNumber(documentNumber.value()).map(this::toDomain);
	}

	@Override
	public Optional<Customer> findByEmail(Email email) {
		return jpaCustomerRepository.findByEmail(email.value()).map(this::toDomain);
	}

	@Override
	public List<Customer> findAll() {
		return jpaCustomerRepository.findAll().stream()
				.map(this::toDomain)
				.collect(Collectors.toList());
	}

	private CustomerEntity toEntity(Customer customer) {
		CustomerEntity entity = new CustomerEntity();
		entity.setId(customer.getId().value());
		entity.setDocumentType(customer.getDocumentType());
		entity.setDocumentNumber(customer.getDocumentNumber().value());
		entity.setFullName(customer.getFullName());
		entity.setEmail(customer.getEmail().value());
		entity.setRegisteredAt(customer.getRegisteredAt());
		entity.setStatus(customer.getStatus());
		return entity;
	}

	private Customer toDomain(CustomerEntity entity) {
		return new Customer(
				CustomerId.of(entity.getId()),
				entity.getDocumentType(),
				DocumentNumber.of(entity.getDocumentNumber()),
				entity.getFullName(),
				Email.of(entity.getEmail()),
				entity.getRegisteredAt(),
				entity.getStatus()
		);
	}
}
