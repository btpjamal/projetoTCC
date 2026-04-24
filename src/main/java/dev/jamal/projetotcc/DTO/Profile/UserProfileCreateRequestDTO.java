package dev.jamal.projetotcc.DTO.Profile;

import dev.jamal.projetotcc.Entities.UserProfile;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileCreateRequestDTO {
    // esse DTO liga o perfil a um usuário existente

    private Long userId;
    private Double tempoDisponivel;
    private Double orcamento;
    private UserProfile.NivelSocial nivelSocial;
}
