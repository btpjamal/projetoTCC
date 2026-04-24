package dev.jamal.projetotcc.DTO.Feedback;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackCreateRequestDTO {

    private Long userId;
    private Long hobbyId;
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