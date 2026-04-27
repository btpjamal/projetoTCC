package dev.jamal.projetotcc.Mapper;

import dev.jamal.projetotcc.DTO.Recommendation.HobbyRecommendationDTO;
import dev.jamal.projetotcc.Entities.Hobby;
import org.springframework.stereotype.Component;

@Component
public class RecommendationMapper {

    public HobbyRecommendationDTO toDTO(
            Hobby hobby,
            Double score,
            String motivo
    ){
        return new HobbyRecommendationDTO(
                hobby.getId(),
                hobby.getNome(),
                hobby.getDescricao(),
                hobby.getCategory().getNome(),
                score,
                motivo
        );
    }
}
