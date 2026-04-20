package dev.jamal.projetotcc.Repository;

import dev.jamal.projetotcc.Entities.UserInterest;
import dev.jamal.projetotcc.Entities.UserInterestId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInterestRepository extends JpaRepository<UserInterest, UserInterestId> {

    List<UserInterest> findByUserId(Long userId);
}
