package com.bank.onboarding.onboarding.application.usecase;

import com.bank.onboarding.onboarding.application.dto.CustomerResponse;
import com.bank.onboarding.onboarding.domain.model.aggregate.Customer;
import com.bank.onboarding.onboarding.domain.port.repository.CustomerRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetAllCustomersUseCase {
	private final CustomerRepository customerRepository;

	public List<CustomerResponse> execute() {
		return customerRepository.findAll().stream()
				.map(this::toResponse)
				.collect(Collectors.toList());
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
