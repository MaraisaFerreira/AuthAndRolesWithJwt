package study.mf.AuthAndRolesWithJwt.infra.security.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import study.mf.AuthAndRolesWithJwt.infra.security.dto.request.UpdateUSerRoleRequestDto;
import study.mf.AuthAndRolesWithJwt.infra.security.dto.request.LoginRequestDto;
import study.mf.AuthAndRolesWithJwt.infra.security.dto.request.RegisterRequestDto;
import study.mf.AuthAndRolesWithJwt.infra.security.dto.response.LoginResponseDto;
import study.mf.AuthAndRolesWithJwt.infra.security.dto.response.RegisterResponseDto;
import study.mf.AuthAndRolesWithJwt.infra.security.dto.response.UpdateUserRoleResponseDto;
import study.mf.AuthAndRolesWithJwt.infra.security.entity.User;
import study.mf.AuthAndRolesWithJwt.infra.security.service.TokenService;
import study.mf.AuthAndRolesWithJwt.infra.security.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthController(UserService userService, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@Valid @RequestBody RegisterRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(requestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto requestDto) {
        var userAndPass = new UsernamePasswordAuthenticationToken(requestDto.email(), requestDto.password());
        var authentication = authenticationManager.authenticate(userAndPass);

        String token = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDto(
                "Success",
                token
        ));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/changeRole")
    public ResponseEntity<UpdateUserRoleResponseDto> changeRole(@Valid @RequestBody UpdateUSerRoleRequestDto requestDto){
        return ResponseEntity.ok(userService.changeRole(requestDto));
    }
}
