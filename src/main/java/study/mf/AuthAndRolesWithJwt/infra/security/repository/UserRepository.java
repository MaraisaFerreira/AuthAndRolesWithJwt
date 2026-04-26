package study.mf.AuthAndRolesWithJwt.infra.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.mf.AuthAndRolesWithJwt.infra.security.entity.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
