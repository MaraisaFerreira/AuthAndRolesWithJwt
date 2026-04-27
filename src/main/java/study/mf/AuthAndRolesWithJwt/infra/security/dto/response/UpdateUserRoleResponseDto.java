package study.mf.AuthAndRolesWithJwt.infra.security.dto.response;

import study.mf.AuthAndRolesWithJwt.infra.security.entity.User;
import study.mf.AuthAndRolesWithJwt.infra.security.entity.enums.Roles;

public record UpdateUserRoleResponseDto(
        String message,
        String name,
        Roles role
) {
    public UpdateUserRoleResponseDto(User user){
        this(
                "Success",
                String.format("%s %s", user.getFirstName(), user.getLastName()),
                user.getRole()
        );
    }
}
