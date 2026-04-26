package study.mf.AuthAndRolesWithJwt.infra.security.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import study.mf.AuthAndRolesWithJwt.infra.security.entity.enums.Roles;

public record RegisterRequestDto(
        @NotBlank(message = "FirstName cannot be null or empty.") String firstName,
        @NotBlank(message = "LastName cannot be null or empty.") String lastName,
        @NotBlank(message = "Email cannot be null or empty.") String email,
        @NotNull(message = "Role cannot be null or empty") Roles role
) {
}
