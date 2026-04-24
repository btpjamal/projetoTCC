package dev.jamal.projetotcc.Mapper;

import dev.jamal.projetotcc.DTO.Category.HobbyCategoryResponseDTO;
import dev.jamal.projetotcc.DTO.Hobby.HobbyCreateRequestDTO;
import dev.jamal.projetotcc.DTO.Hobby.HobbyResponseDTO;
import dev.jamal.projetotcc.DTO.Hobby.HobbySummaryDTO;
import dev.jamal.projetotcc.Entities.Hobby;
import dev.jamal.projetotcc.Entities.HobbyCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HobbyMapper {

    private final HobbyCategoryMapper categoryMapper;

    public Hobby toEntity(HobbyCreateRequestDTO dto, HobbyCategory category){
        Hobby hobby = new Hobby();
        hobby.setNome(dto.getNome());
        hobby.setDescricao(dto.getDescricao());
        hobby.setCustoEstimado(dto.getCustoEstimado());
        hobby.setNivelDificuldade(dto.getNivelDificuldade());
        hobby.setTempoNecessario(dto.getTempoNecessario());
        hobby.setTipoSocializacao(dto.getTipoSocializacao());
        hobby.setCategory(category);
        return hobby;
    }

    public HobbyResponseDTO toResponseDTO(Hobby hobby){
        return new HobbyResponseDTO(
                hobby.getId(),
                hobby.getNome(),
                hobby.getDescricao(),
                hobby.getCustoEstimado(),
                hobby.getNivelDificuldade(),
                hobby.getTempoNecessario(),
                hobby.getTipoSocializacao(),
                categoryMapper.toResponseDTO(hobby.getCategory())
        );
    }

    public HobbySummaryDTO toSummaryDTO(Hobby hobby){
        return new HobbySummaryDTO(
                hobby.getId(),
                hobby.getNome(),
                hobby.getCategory().getNome() // pode causar LazyInitializationException se a categoria não estiver carregada
        );
    }

}
