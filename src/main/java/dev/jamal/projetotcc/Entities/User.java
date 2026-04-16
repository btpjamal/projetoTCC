package dev.jamal.projetotcc.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    // Column -> serve para configurar a coluna
    // unique = true -> cada valor é unico, não pode repetir
    // nullable = false -> obrigatório, não deve ser um valor nulo
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    private LocalDate dataCadastro;

    @PrePersist
    public void prePersist(){
        this.dataCadastro = LocalDate.now();
    }


    // relacionamento de um para um
    // um usuário tem um perfil
    // lado com "mappedBy" é o lado inverso da relação
    // cascade = CascadeType.ALL -> quando salvar o pai, salva o filho junto
    // fetch = FetchType.LAZY -> evita carregar tudo sempre
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore // para evitar loop de serialização
    private UserProfile profile;

}
