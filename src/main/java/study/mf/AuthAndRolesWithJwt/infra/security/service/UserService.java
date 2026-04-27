package study.mf.AuthAndRolesWithJwt.infra.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.mf.AuthAndRolesWithJwt.infra.security.dto.request.UpdateUSerRoleRequestDto;
import study.mf.AuthAndRolesWithJwt.infra.security.dto.request.RegisterRequestDto;
import study.mf.AuthAndRolesWithJwt.infra.security.dto.response.RegisterResponseDto;
import study.mf.AuthAndRolesWithJwt.infra.security.dto.response.UpdateUserRoleResponseDto;
import study.mf.AuthAndRolesWithJwt.infra.security.entity.User;
import study.mf.AuthAndRolesWithJwt.infra.security.entity.enums.Roles;
import study.mf.AuthAndRolesWithJwt.infra.security.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public RegisterResponseDto register(RegisterRequestDto requestDto) {
        User user = userRepository.save(new User(
                null,
                requestDto.firstName(),
                requestDto.lastName(),
                requestDto.email(),
                passwordEncoder.encode(requestDto.password()),
                Roles.BASIC
        ));

        return new RegisterResponseDto(user);
    }

    @Transactional
    public UpdateUserRoleResponseDto changeRole(UpdateUSerRoleRequestDto requestDto) {
        User user = userRepository.findByEmail(requestDto.email())
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));
        user.setRole(requestDto.role());
        return new UpdateUserRoleResponseDto(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found!"));
    }
}
