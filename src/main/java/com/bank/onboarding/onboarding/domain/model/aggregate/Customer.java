package com.bank.onboarding.onboarding.domain.model.aggregate;

import com.bank.onboarding.onboarding.domain.model.valueobject.CustomerId;
import com.bank.onboarding.onboarding.domain.model.valueobject.CustomerStatus;
import com.bank.onboarding.onboarding.domain.model.valueobject.DocumentNumber;
import com.bank.onboarding.onboarding.domain.model.valueobject.DocumentType;
import com.bank.onboarding.onboarding.domain.model.valueobject.Email;
import java.time.OffsetDateTime;
import java.util.Objects;
import lombok.Getter;

@Getter
public class Customer {
	private final CustomerId id;
	private final DocumentType documentType;
	private final DocumentNumber documentNumber;
	private final String fullName;
	private final Email email;
	private final OffsetDateTime registeredAt;
	private final CustomerStatus status;

	public Customer(CustomerId id, DocumentType documentType, DocumentNumber documentNumber, String fullName, Email email, OffsetDateTime registeredAt, CustomerStatus status) {
		this.id = Objects.requireNonNull(id, "id");
		this.documentType = Objects.requireNonNull(documentType, "documentType");
		this.documentNumber = Objects.requireNonNull(documentNumber, "documentNumber");
		this.fullName = requireText(fullName, "fullName");
		this.email = Objects.requireNonNull(email, "email");
		this.registeredAt = Objects.requireNonNull(registeredAt, "registeredAt");
		this.status = Objects.requireNonNull(status, "status");
	}

	public static Customer createNew(DocumentType documentType, DocumentNumber documentNumber, String fullName, Email email) {
		return new Customer(CustomerId.newId(), documentType, documentNumber, fullName, email, OffsetDateTime.now(), CustomerStatus.ACTIVE);
	}

	private static String requireText(String value, String field) {
		if (value == null || value.isBlank()) {
			throw new IllegalArgumentException(field + " is required");
		}
		return value;
	}
}
