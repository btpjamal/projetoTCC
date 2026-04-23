package dev.jamal.projetotcc.Service;

import dev.jamal.projetotcc.Entities.UserHobbyFeedback;
import dev.jamal.projetotcc.Repository.UserHobbyFeedbackRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FeedbackService {

    private final UserHobbyFeedbackRepository userHobbyFeedbackRepository;

    public UserHobbyFeedback salvar(UserHobbyFeedback feedback){
        return userHobbyFeedbackRepository.save(feedback);
    }

    public List<UserHobbyFeedback> listarPorUsuario(Long userId){
        return userHobbyFeedbackRepository.buscarComHobby(userId);
    }
}
