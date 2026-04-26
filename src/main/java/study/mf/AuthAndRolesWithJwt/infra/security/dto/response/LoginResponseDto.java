package study.mf.AuthAndRolesWithJwt.infra.security.dto.response;

public record LoginResponseDto(
        String message,
        String accessToken
) {
}
