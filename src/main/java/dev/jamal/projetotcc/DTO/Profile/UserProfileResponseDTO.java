package dev.jamal.projetotcc.DTO.Profile;

import dev.jamal.projetotcc.DTO.User.UserSummaryDTO;
import dev.jamal.projetotcc.Enum.NivelSocial;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileResponseDTO {

    private Long id;
    private Double tempoDisponivel;
    private Double orcamento;
    private NivelSocial nivelSocial;
    private UserSummaryDTO user;
}
