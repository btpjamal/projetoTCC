package dev.jamal.projetotcc.Repository;

import dev.jamal.projetotcc.Entities.UserHobbyFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserHobbyFeedbackRepository extends JpaRepository<UserHobbyFeedback, Long> {

    @Query("""
        SELECT f
        FROM UserHobbyFeedback f
        JOIN FETCH f.hobby h
        JOIN FETCH h.category    
        JOIN FETCH f.user    
        WHERE f.user.id =:userId        
    """)
    List<UserHobbyFeedback> buscarComHobbyEUsuario(@Param("userId") Long userId);

}
