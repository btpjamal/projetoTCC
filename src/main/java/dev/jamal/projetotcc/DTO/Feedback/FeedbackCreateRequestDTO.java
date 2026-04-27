package dev.jamal.projetotcc.DTO.Feedback;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackCreateRequestDTO {

    @NotNull
    private Long userId;

    @NotNull
    private Long hobbyId;

    @NotNull
    @Min(1)
    @Max(5)
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