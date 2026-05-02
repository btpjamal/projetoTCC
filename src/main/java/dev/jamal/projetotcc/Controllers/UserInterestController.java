package dev.jamal.projetotcc.Controllers;

import dev.jamal.projetotcc.DTO.Interest.UserInterestCreateRequestDTO;
import dev.jamal.projetotcc.DTO.Interest.UserInterestResponseDTO;
import dev.jamal.projetotcc.Service.UserInterestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/{userId}/interests")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserInterestController {

    private final UserInterestService userInterestService;

    @GetMapping
    public ResponseEntity<List<UserInterestResponseDTO>> listar(
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(
                userInterestService.listarPorUsuario(userId)
        );
    }

    @PostMapping
    public ResponseEntity<UserInterestResponseDTO> adicionarOuAtualizar(
            @PathVariable Long userId,
            @RequestBody UserInterestCreateRequestDTO dto
    ) {
        return ResponseEntity.ok(
                userInterestService.adicionarOuAtualizar(userId, dto)
        );
    }
}
