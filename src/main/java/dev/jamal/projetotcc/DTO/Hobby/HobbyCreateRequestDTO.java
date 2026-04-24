package dev.jamal.projetotcc.DTO.Hobby;

import dev.jamal.projetotcc.Entities.Hobby;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HobbyCreateRequestDTO {

    private String nome;
    private String descricao;
    private Double custoEstimado;
    private Integer nivelDificuldade;
    private Double tempoNecessario;
    private Hobby.TipoSocial tipoSocializacao;
    private Long categoryId; // o cliente não deve mandar uma entidade inteira, só a categoria
}
