package study.mf.AuthAndRolesWithJwt.infra.security.dto.response;

import study.mf.AuthAndRolesWithJwt.infra.security.entity.enums.Roles;

public record UserTokenDataDto(
        String email,
        Roles role
) {
}
