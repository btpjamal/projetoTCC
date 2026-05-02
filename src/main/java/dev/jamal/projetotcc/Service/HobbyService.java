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


    @Transactional
    public List<HobbyResponseDTO> listarTodos() {
        return hobbyRepository.findAllWithCategory()
                .stream()
                .map(hobbyMapper::toResponseDTO)
                .toList();
    }

    @Transactional
    public HobbyResponseDTO buscarPorId(Long id) {
        Hobby hobby = hobbyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hobby não encontrado."));

        return hobbyMapper.toResponseDTO(hobby);
    }

    public HobbyResponseDTO salvar(HobbyCreateRequestDTO dto) {

        HobbyCategory category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada."));

        Hobby hobby = hobbyMapper.toEntity(dto, category);

        Hobby salvo = hobbyRepository.save(hobby);

        return hobbyMapper.toResponseDTO(salvo);
    }

    public HobbyResponseDTO atualizar(Long id, HobbyCreateRequestDTO dto) {
        Hobby hobby = hobbyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hobby não encontrado."));

        HobbyCategory category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada."));

        hobby.setNome(dto.getNome());
        hobby.setDescricao(dto.getDescricao());
        hobby.setCustoEstimado(dto.getCustoEstimado());
        hobby.setNivelDificuldade(dto.getNivelDificuldade());
        hobby.setTempoNecessario(dto.getTempoNecessario());
        hobby.setTipoSocializacao(dto.getTipoSocializacao());
        hobby.setCategory(category);

        Hobby atualizado = hobbyRepository.save(hobby);

        return hobbyMapper.toResponseDTO(atualizado);
    }

    public void deletar(Long id) {
        if (!hobbyRepository.existsById(id)) {
            throw new RuntimeException("Hobby não encontrado");
        }

        hobbyRepository.deleteById(id);
    }

    // para uso interno da aplicação, não deve ser consumido por controllers
    @Transactional
    public Hobby buscarEntidadePorId(Long id) {
        return hobbyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hobby não encontrado."));
    }
}
