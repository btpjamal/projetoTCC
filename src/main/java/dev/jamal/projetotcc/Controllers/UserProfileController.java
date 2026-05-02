package dev.jamal.projetotcc.Controllers;

import dev.jamal.projetotcc.DTO.Profile.UserProfileCreateRequestDTO;
import dev.jamal.projetotcc.DTO.Profile.UserProfileResponseDTO;
import dev.jamal.projetotcc.Service.UserProfileService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/{userId}/profile")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping
    public ResponseEntity<UserProfileResponseDTO> buscarPerfil(
        @PathVariable Long userId
    ) {
        return ResponseEntity.ok(
                userProfileService.buscarPorUsuario(userId)
        );
    }

    @PutMapping
    public ResponseEntity<UserProfileResponseDTO> criarOuAtualizarPerfil(
            @PathVariable Long userId,
            @RequestBody UserProfileCreateRequestDTO dto
    ) {
        return ResponseEntity.ok(userProfileService.criarOuAtualizar(userId, dto));
    }
}
