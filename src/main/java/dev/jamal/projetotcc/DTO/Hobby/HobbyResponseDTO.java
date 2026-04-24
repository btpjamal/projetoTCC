package dev.jamal.projetotcc.DTO.Hobby;

import dev.jamal.projetotcc.DTO.Category.HobbyCategoryResponseDTO;
import dev.jamal.projetotcc.Entities.Hobby;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HobbyResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
    private Double custoEstimado;
    private Integer nivelDificuldade;
    private Double tempoNecessario;
    private Hobby.TipoSocial tipoSocializacao;
    private HobbyCategoryResponseDTO category;
}
