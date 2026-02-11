package com.bank.onboarding.onboarding.interfaces.rest.dto;

import java.time.OffsetDateTime;

public record ApiResponse<T>(
		boolean success,
		String message,
		T data,
		String timestamp,
		String code
) {
	public static <T> ApiResponse<T> success(String message, T data, String code) {
		return new ApiResponse<>(true, message, data, OffsetDateTime.now().toString(), code);
	}

	public static <T> ApiResponse<T> error(String message, T data, String code) {
		return new ApiResponse<>(false, message, data, OffsetDateTime.now().toString(), code);
	}
}
