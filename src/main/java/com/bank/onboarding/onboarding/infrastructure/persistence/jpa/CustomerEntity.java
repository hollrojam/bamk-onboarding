package com.bank.onboarding.onboarding.infrastructure.persistence.jpa;

import com.bank.onboarding.onboarding.domain.model.valueobject.CustomerStatus;
import com.bank.onboarding.onboarding.domain.model.valueobject.DocumentType;
import java.time.OffsetDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
public class CustomerEntity {
	@Id
	private String id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private DocumentType documentType;

	@Column(nullable = false)
	private String documentNumber;

	@Column(nullable = false)
	private String fullName;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private OffsetDateTime registeredAt;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private CustomerStatus status;

}
