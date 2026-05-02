package dev.jamal.projetotcc.Controllers;

import dev.jamal.projetotcc.DTO.User.UserCreateRequestDTO;
import dev.jamal.projetotcc.DTO.User.UserResponseDTO;
import dev.jamal.projetotcc.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    // criar usuário
    // POST /api/v1/users
    @PostMapping
    public ResponseEntity<UserResponseDTO> cadastrar(
            @RequestBody @Valid UserCreateRequestDTO dto
    ) {
        UserResponseDTO response = userService.cadastrar(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // buscar por ID
    // GET /api/v1/user/1
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> buscarPorId(
        @PathVariable Long id
    ) {
        UserResponseDTO response = userService.buscarPorId(id);

        return ResponseEntity.ok(response);
    }

    // atualizar usuário
    // PUT /api/v1/users/1
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> atualizar(
        @PathVariable Long id,
        @RequestBody @Valid UserCreateRequestDTO dto
    ) {
        UserResponseDTO response = userService.atualizar(id, dto);
        return ResponseEntity.ok(response);
    }

    // deletar usuário
    // DELETE /api/v1/users/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
        @PathVariable Long id
    ) {
        userService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
