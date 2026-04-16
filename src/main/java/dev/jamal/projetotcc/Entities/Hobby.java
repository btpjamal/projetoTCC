package dev.jamal.projetotcc.Entities;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "hobby")
public class Hobby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;
    private String descricao;
    private Double custoEstimado;
    private Integer nivelDificuldade;
    private Double tempoNecessario;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoSocial tipoSocializacao;

    public enum TipoSocial {
        INDIVIDUAL,
        SOCIAL
    }

    // muitos hobbies pertencem a uma categoria
    // JoinColumn -> lado dono da relação
    // fetch = FetchType.LAZY -> evita carregar a categoria sempre
    // nullable = false -> hobbies precisam ter uma categoria
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private HobbyCategory category;

}
