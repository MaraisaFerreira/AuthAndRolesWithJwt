package study.mf.AuthAndRolesWithJwt.infra.security.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.mf.AuthAndRolesWithJwt.infra.security.dto.request.RegisterRequestDto;
import study.mf.AuthAndRolesWithJwt.infra.security.dto.response.RegisterResponseDto;
import study.mf.AuthAndRolesWithJwt.infra.security.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@Valid @RequestBody RegisterRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(requestDto));
    }
}
