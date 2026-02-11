package com.bank.onboarding.onboarding.application.usecase;

import com.bank.onboarding.onboarding.application.dto.CustomerRequest;
import com.bank.onboarding.onboarding.application.dto.CustomerResponse;
import com.bank.onboarding.onboarding.domain.exception.DuplicateCustomerException;
import com.bank.onboarding.onboarding.domain.model.aggregate.Customer;
import com.bank.onboarding.onboarding.domain.model.valueobject.DocumentNumber;
import com.bank.onboarding.onboarding.domain.model.valueobject.Email;
import com.bank.onboarding.onboarding.domain.port.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateCustomerUseCase {
	private final CustomerRepository customerRepository;

	public CustomerResponse execute(CustomerRequest request) {
		DocumentNumber documentNumber = DocumentNumber.of(request.documentNumber());
		Email email = Email.of(request.email());
		if (customerRepository.findByDocumentNumber(documentNumber).isPresent()) {
			throw new DuplicateCustomerException("El número de documento ya existe");
		}
		if (customerRepository.findByEmail(email).isPresent()) {
			throw new DuplicateCustomerException("El email ya existe");
		}
		Customer customer = Customer.createNew(request.documentType(), documentNumber, request.nombre(), email);
		Customer saved = customerRepository.save(customer);
		return toResponse(saved);
	}

	private CustomerResponse toResponse(Customer customer) {
		return new CustomerResponse(
				customer.getId().value(),
				customer.getFullName(),
				customer.getEmail().value(),
				customer.getRegisteredAt().toString(),
				customer.getStatus().name()
		);
	}
}
