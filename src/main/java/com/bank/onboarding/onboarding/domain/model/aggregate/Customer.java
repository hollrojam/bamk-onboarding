package com.bank.onboarding.onboarding.domain.model.aggregate;

import com.bank.onboarding.onboarding.domain.model.valueobject.CustomerId;
import com.bank.onboarding.onboarding.domain.model.valueobject.DocumentNumber;
import com.bank.onboarding.onboarding.domain.model.valueobject.DocumentType;
import com.bank.onboarding.onboarding.domain.model.valueobject.Email;
import java.util.Objects;

public class Customer {
	private final CustomerId id;
	private final DocumentType documentType;
	private final DocumentNumber documentNumber;
	private final String fullName;
	private final Email email;

	public Customer(CustomerId id, DocumentType documentType, DocumentNumber documentNumber, String fullName, Email email) {
		this.id = Objects.requireNonNull(id, "id");
		this.documentType = Objects.requireNonNull(documentType, "documentType");
		this.documentNumber = Objects.requireNonNull(documentNumber, "documentNumber");
		this.fullName = requireText(fullName, "fullName");
		this.email = Objects.requireNonNull(email, "email");
	}

	public static Customer createNew(DocumentType documentType, DocumentNumber documentNumber, String fullName, Email email) {
		return new Customer(CustomerId.newId(), documentType, documentNumber, fullName, email);
	}

	public CustomerId getId() {
		return id;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public DocumentNumber getDocumentNumber() {
		return documentNumber;
	}

	public String getFullName() {
		return fullName;
	}

	public Email getEmail() {
		return email;
	}

	private static String requireText(String value, String field) {
		if (value == null || value.isBlank()) {
			throw new IllegalArgumentException(field + " is required");
		}
		return value;
	}
}
