package study.mf.AuthAndRolesWithJwt.infra.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import study.mf.AuthAndRolesWithJwt.infra.security.dto.response.UserTokenDataDto;
import study.mf.AuthAndRolesWithJwt.infra.security.entity.User;
import study.mf.AuthAndRolesWithJwt.infra.security.entity.enums.Roles;

import java.time.Instant;

@Service
public class TokenService {

    @Value("${api.token.secret}")
    private String secret;

    public String generateToken(User user) {
        return JWT.create()
                .withIssuer("AuthAndRolesWithJwt-API")
                .withSubject(user.getEmail())
                .withClaim("role", user.getRole().toString())
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusSeconds(3600))
                .sign(Algorithm.HMAC256(secret));
    }

    public UserTokenDataDto decodeToken(String token){
       DecodedJWT decoded = JWT.require(Algorithm.HMAC256(secret))
                .withIssuer("AuthAndRolesWithJwt-API")
                .build()
                .verify(token);

       return new UserTokenDataDto(
               decoded.getSubject(),
               Roles.valueOf(decoded.getClaim("role").asString())
       );
    }
}
