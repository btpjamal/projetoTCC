package dev.jamal.projetotcc.Service;

import dev.jamal.projetotcc.DTO.Profile.UserProfileCreateRequestDTO;
import dev.jamal.projetotcc.DTO.Profile.UserProfileResponseDTO;
import dev.jamal.projetotcc.Entities.User;
import dev.jamal.projetotcc.Entities.UserProfile;
import dev.jamal.projetotcc.Mapper.UserProfileMapper;
import dev.jamal.projetotcc.Repository.UserProfileRepository;
import dev.jamal.projetotcc.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;
    private final UserProfileMapper userProfileMapper;

    @Transactional
    public UserProfileResponseDTO buscarPorUsuario(Long userId) {
        UserProfile profile = userProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Perfil do usuário não encontrado."));

        return userProfileMapper.toResponseDTO(profile);
    }

    public UserProfileResponseDTO criarOuAtualizar(
            Long userId,
            UserProfileCreateRequestDTO dto
    ) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        UserProfile profile = userProfileRepository.findByUserId(userId)
                .orElseGet(UserProfile::new);

        profile.setUser(user);
        profile.setTempoDisponivel(dto.getTempoDisponivel());
        profile.setOrcamento(dto.getOrcamento());
        profile.setNivelSocial(dto.getNivelSocial());

        UserProfile salvo = userProfileRepository.save(profile);

        return userProfileMapper.toResponseDTO(salvo);
    }
}
