package dev.jamal.projetotcc.Mapper;

import dev.jamal.projetotcc.DTO.Profile.UserProfileCreateRequestDTO;
import dev.jamal.projetotcc.DTO.Profile.UserProfileResponseDTO;
import dev.jamal.projetotcc.DTO.User.UserCreateRequestDTO;
import dev.jamal.projetotcc.Entities.User;
import dev.jamal.projetotcc.Entities.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProfileMapper {

    private final UserMapper userMapper;

    public UserProfile toEntity(UserProfileCreateRequestDTO dto, User user){
        UserProfile profile = new UserProfile();
        profile.setTempoDisponivel(dto.getTempoDisponivel());
        profile.setOrcamento(dto.getOrcamento());
        profile.setNivelSocial(dto.getNivelSocial());
        profile.setUser(user);
        return profile;
    }

    public UserProfileResponseDTO toResponseDTO(UserProfile profile){
        return new UserProfileResponseDTO(
          profile.getId(),
          profile.getTempoDisponivel(),
          profile.getOrcamento(),
          profile.getNivelSocial(),
          userMapper.toSummaryDTO(profile.getUser())
        );
    }
}
