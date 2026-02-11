package com.bank.onboarding.onboarding.infrastructure.configuration;

import com.bank.onboarding.onboarding.application.usecase.CreateAccountUseCase;
import com.bank.onboarding.onboarding.application.usecase.CreateCustomerUseCase;
import com.bank.onboarding.onboarding.application.usecase.GetAccountsByCustomerUseCase;
import com.bank.onboarding.onboarding.application.usecase.GetAllCustomersUseCase;
import com.bank.onboarding.onboarding.domain.model.valueobject.AccountNumber;
import com.bank.onboarding.onboarding.domain.port.repository.AccountRepository;
import com.bank.onboarding.onboarding.domain.port.repository.CustomerRepository;
import com.bank.onboarding.onboarding.domain.service.AccountNumberGenerator;
import java.util.UUID;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
	@Bean
	public AccountNumberGenerator accountNumberGenerator() {
		return () -> AccountNumber.of(UUID.randomUUID().toString().replace("-", ""));
	}

	@Bean
	public CreateCustomerUseCase createCustomerUseCase(CustomerRepository customerRepository) {
		return new CreateCustomerUseCase(customerRepository);
	}

	@Bean
	public GetAllCustomersUseCase getAllCustomersUseCase(CustomerRepository customerRepository) {
		return new GetAllCustomersUseCase(customerRepository);
	}

	@Bean
	public CreateAccountUseCase createAccountUseCase(AccountRepository accountRepository, CustomerRepository customerRepository, AccountNumberGenerator accountNumberGenerator) {
		return new CreateAccountUseCase(accountRepository, customerRepository, accountNumberGenerator);
	}

	@Bean
	public GetAccountsByCustomerUseCase getAccountsByCustomerUseCase(AccountRepository accountRepository, CustomerRepository customerRepository) {
		return new GetAccountsByCustomerUseCase(accountRepository, customerRepository);
	}
}
