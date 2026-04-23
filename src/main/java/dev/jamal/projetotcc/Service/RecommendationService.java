package dev.jamal.projetotcc.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {


    // ainda precisa evoluir
    public List<String> recomendar(){
        return List.of("Corrida", "Xadrez", "Fotografia");
    }
}
