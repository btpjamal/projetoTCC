package dev.jamal.projetotcc.DTO.Recommendation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HobbyRecommendationDTO {

    private Long hobbyId;
    private String nome;
    private String descricao;
    private String categoria;
    private Double score;
    private String motivo;
}
/*

EXEMPLO DE RESPOSTA JSON

{
  "hobbyId": 1,
  "nome": "Corrida",
  "descricao": "Atividade física ao ar livre",
  "categoria": "Esporte",
  "score": 87.5,
  "motivo": "Recomendado por ser de baixo custo, individual e compatível com seu tempo disponível."
}

 */