package dev.jamal.projetotcc.DTO.Profile;

import dev.jamal.projetotcc.Enum.NivelSocial;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileCreateRequestDTO {
    // esse DTO liga o perfil a um usuário existente

    @NotNull(message = "O tempo disponível é obrigatório")
    private Double tempoDisponivel;

    @NotNull(message = "O orçamento é obrigatório")
    private Double orcamento;

    @NotNull(message = "O nível social é obrigatório")
    private NivelSocial nivelSocial;
}
