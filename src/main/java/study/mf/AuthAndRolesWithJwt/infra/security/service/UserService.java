package study.mf.AuthAndRolesWithJwt.infra.security.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import study.mf.AuthAndRolesWithJwt.infra.security.dto.request.RegisterRequestDto;
import study.mf.AuthAndRolesWithJwt.infra.security.dto.response.RegisterResponseDto;
import study.mf.AuthAndRolesWithJwt.infra.security.entity.User;
import study.mf.AuthAndRolesWithJwt.infra.security.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public RegisterResponseDto register(RegisterRequestDto requestDto){
        User user = userRepository.save(new User(
                null,
                requestDto.firstName(),
                requestDto.lastName(),
                requestDto.email(),
                passwordEncoder.encode(requestDto.password()),
                requestDto.role()
        ));

        return new RegisterResponseDto(user);
    }
}
