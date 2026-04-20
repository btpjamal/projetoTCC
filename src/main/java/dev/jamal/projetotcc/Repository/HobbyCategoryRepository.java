package dev.jamal.projetotcc.Repository;

import dev.jamal.projetotcc.Entities.HobbyCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HobbyCategoryRepository extends JpaRepository<HobbyCategory, Long> {
}
