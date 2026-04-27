package study.mf.AuthAndRolesWithJwt.infra.security.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import study.mf.AuthAndRolesWithJwt.infra.security.entity.enums.Roles;

public record UpdateUSerRoleRequestDto(
        @Email(message = "Invalid email format", regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
        @NotBlank(message = "Email cannot be null or empty.")
        String email,
        @NotNull(message = "Role cannot be null or empty.")
        Roles role
) {
}
