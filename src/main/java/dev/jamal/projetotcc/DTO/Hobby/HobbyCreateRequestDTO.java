package dev.jamal.projetotcc.DTO.Hobby;

import dev.jamal.projetotcc.Entities.Hobby;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HobbyCreateRequestDTO {

    @NotBlank
    private String nome;

    private String descricao;

    @NotNull
    @PositiveOrZero
    private Double custoEstimado;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer nivelDificuldade;

    @NotNull
    @Positive
    private Double tempoNecessario;

    @NotNull
    private Hobby.TipoSocial tipoSocializacao;

    @NotNull
    private Long categoryId; // o cliente não deve mandar uma entidade inteira, só a categoria
}
