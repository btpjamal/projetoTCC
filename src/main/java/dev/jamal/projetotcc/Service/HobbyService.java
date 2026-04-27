package dev.jamal.projetotcc.Service;

import dev.jamal.projetotcc.DTO.Hobby.HobbyCreateRequestDTO;
import dev.jamal.projetotcc.DTO.Hobby.HobbyResponseDTO;
import dev.jamal.projetotcc.Entities.Hobby;
import dev.jamal.projetotcc.Entities.HobbyCategory;
import dev.jamal.projetotcc.Mapper.HobbyMapper;
import dev.jamal.projetotcc.Repository.HobbyCategoryRepository;
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
    private final HobbyCategoryRepository categoryRepository;
    private final HobbyMapper hobbyMapper;

    public HobbyResponseDTO cadastrar(HobbyCreateRequestDTO dto) {

        HobbyCategory category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada."));

        Hobby hobby = hobbyMapper.toEntity(dto, category);
        Hobby salvo = hobbyRepository.save(hobby);

        return hobbyMapper.toResponseDTO(salvo);
    }


    @Transactional
    public List<HobbyResponseDTO> listarTodos() {
        return hobbyRepository.findAllWithCategory()
                .stream()
                .map(hobbyMapper::toResponseDTO)
                .toList();
    }

    @Transactional
    public Hobby buscarEntidadePorId(Long id) {
        return hobbyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hobby não encontrado."));
    }
}
