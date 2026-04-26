package study.mf.AuthAndRolesWithJwt.infra.security.dto.response;

import study.mf.AuthAndRolesWithJwt.infra.security.entity.enums.Roles;

public record UserTokenDto(
        String email,
        Roles role
) {
}
