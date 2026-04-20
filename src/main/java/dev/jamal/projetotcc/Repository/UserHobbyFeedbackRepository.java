package dev.jamal.projetotcc.Repository;

import dev.jamal.projetotcc.Entities.UserHobbyFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserHobbyFeedbackRepository extends JpaRepository<UserHobbyFeedback, Long> {

    List<UserHobbyFeedback> findByUserId(Long userId);

}
