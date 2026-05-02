package dev.jamal.projetotcc.DTO.Feedback;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackCreateRequestDTO {

    @NotNull(message = "O ID do usuário é obrigatório.")
    private Long userId;

    @NotNull(message = "O ID do hobby é obrigatório.")
    private Long hobbyId;

    @NotNull(message = "A nota é obrigatória")
    @Min(value = 1, message = "A nota mínima é 1")
    @Max(value = 5, message = "A nota máxima é 5")
    private Integer rating;
}

/*

EXEMPLO DE JSON QUE O FRONT-END VAI MANDAR PARA O BACK-END

{
    "userId": 1,
    "hobbyId": 3,
    "rating": 5
}

 */