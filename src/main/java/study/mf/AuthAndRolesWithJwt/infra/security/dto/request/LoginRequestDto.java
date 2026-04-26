package study.mf.AuthAndRolesWithJwt.infra.security.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDto(
        @Email(message = "Invalid email format", regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
        @NotBlank(message = "Email cannot be null or empty.")
        String email,
        @NotBlank(message = "Password cannot be null or empty.")
        String password
) {
}
