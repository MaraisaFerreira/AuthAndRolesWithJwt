package study.mf.AuthAndRolesWithJwt.infra.security.component;

import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import study.mf.AuthAndRolesWithJwt.infra.security.dto.response.UserTokenDataDto;
import study.mf.AuthAndRolesWithJwt.infra.security.entity.enums.Roles;
import study.mf.AuthAndRolesWithJwt.infra.security.service.TokenService;

import java.io.IOException;
import java.util.List;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenService tokenService;

    public SecurityFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationToken = request.getHeader("Authorization");
        System.out.println("=================== authorizationToken = " + authorizationToken);
        String token = "";

        if (authorizationToken != null && authorizationToken.startsWith("Bearer ")){
            token = authorizationToken.replace("Bearer ", "");
        }

        if (!token.isBlank()){
            try {
                UserTokenDataDto userData = tokenService.decodeToken(token);
                List<SimpleGrantedAuthority> authorities = getUserAuthorities(userData.role());

                var authentication = new UsernamePasswordAuthenticationToken(userData.email(), null, authorities);

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (JWTVerificationException ex) {
                SecurityContextHolder.clearContext();
            }
        }

        filterChain.doFilter(request, response);
    }

    private List<SimpleGrantedAuthority> getUserAuthorities(Roles role) {
        return switch (role) {
            case ADMIN -> List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_BASIC")
            );
            case BASIC -> List.of(
                    new SimpleGrantedAuthority("ROLE_BASIC")
            );
        };
    }
}
