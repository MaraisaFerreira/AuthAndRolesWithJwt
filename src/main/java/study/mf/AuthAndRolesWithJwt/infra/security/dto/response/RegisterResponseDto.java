package study.mf.AuthAndRolesWithJwt.infra.security.dto.response;

import study.mf.AuthAndRolesWithJwt.infra.security.entity.User;

public record RegisterResponseDto(
        String message,
        String id,
        String email,
        String role
) {
    public RegisterResponseDto(User user) {
        this(
                String.format("%s %s registered!", user.getFirstName(), user.getLastName()),
                user.getId().toString(),
                user.getEmail(),
                user.getRole().name()
        );
    }
}
