package dev.jamal.projetotcc.Entities;

import dev.jamal.projetotcc.Enum.NivelSocial;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_profile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double tempoDisponivel;

    @Column(nullable = false)
    private Double orcamento;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NivelSocial nivelSocial;

    // relacionamento de um para um
    // um perfil tem um usuário
    // lado com "JoinColumn" é o dono da relação
    // nullable = false -> garante integridade no banco
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
