package dev.jamal.projetotcc.DTO.Interest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInterestCreateRequestDTO {

    private Long userId;
    private Long interestId;
    private Integer peso;
}
