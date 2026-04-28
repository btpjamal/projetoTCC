package dev.jamal.projetotcc.Repository;

import dev.jamal.projetotcc.Entities.User;
import dev.jamal.projetotcc.Entities.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    Optional<UserProfile> findByUserId(Long userId);

    Long user(User user);
}
