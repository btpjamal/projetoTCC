package dev.jamal.projetotcc.DTO.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateRequestDTO {

    private String nome;
    private String email;
    private String senha; // aqui entra a senha, porquê o usuário está criando a conta
}
