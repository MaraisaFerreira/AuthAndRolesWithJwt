package study.mf.AuthAndRolesWithJwt.infra.security.service;

import org.springframework.stereotype.Service;
import study.mf.AuthAndRolesWithJwt.infra.security.dto.request.RegisterRequestDto;
import study.mf.AuthAndRolesWithJwt.infra.security.dto.response.RegisterResponseDto;
import study.mf.AuthAndRolesWithJwt.infra.security.entity.User;
import study.mf.AuthAndRolesWithJwt.infra.security.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public RegisterResponseDto register(RegisterRequestDto requestDto){
        User user = userRepository.save(new User(
                null,
                requestDto.firstName(),
                requestDto.lastName(),
                requestDto.email(),
                requestDto.role()
        ));

        return new RegisterResponseDto(user);
    }
}
