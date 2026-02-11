package com.bank.onboarding.onboarding.infrastructure.persistence.jpa;

import com.bank.onboarding.onboarding.domain.model.valueobject.AccountStatus;
import com.bank.onboarding.onboarding.domain.model.valueobject.AccountType;
import java.math.BigDecimal;
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
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
public class AccountEntity {
	@Id
	private String id;

	@Column(nullable = false)
	private String customerId;

	@Column(nullable = false, unique = true)
	private String accountNumber;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private AccountType type;

	@Column(nullable = false)
	private BigDecimal balance;

	@Column(nullable = false)
	private OffsetDateTime openedAt;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private AccountStatus status;

}
