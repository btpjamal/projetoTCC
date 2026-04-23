package dev.jamal.projetotcc.Service;

import dev.jamal.projetotcc.Entities.Hobby;
import dev.jamal.projetotcc.Repository.HobbyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class HobbyService {

    private final HobbyRepository hobbyRepository;

    public Hobby salvar(Hobby hobby) {
        return hobbyRepository.save(hobby);
    }

    public List<Hobby> listarTodos(){
        return hobbyRepository.findAll();
    }

    public List<Hobby> buscarPorCategoria(Long categoryId) {
        return hobbyRepository.findByCategoryId(categoryId);
    }
}
