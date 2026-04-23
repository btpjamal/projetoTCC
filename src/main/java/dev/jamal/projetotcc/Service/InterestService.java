package dev.jamal.projetotcc.Service;

import dev.jamal.projetotcc.Entities.Interest;
import dev.jamal.projetotcc.Repository.InterestRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class InterestService {

    private final InterestRepository interestRepository;

    public List<Interest> listarTodos(){
        return interestRepository.findAll();
    }
}
