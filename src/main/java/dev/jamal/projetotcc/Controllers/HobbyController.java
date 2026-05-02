package dev.jamal.projetotcc.Controllers;

import dev.jamal.projetotcc.DTO.Hobby.HobbyCreateRequestDTO;
import dev.jamal.projetotcc.DTO.Hobby.HobbyResponseDTO;
import dev.jamal.projetotcc.Service.HobbyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hobbies")
@RequiredArgsConstructor
@CrossOrigin("*")
public class HobbyController {

    private final HobbyService hobbyService;

    @GetMapping
    public ResponseEntity<List<HobbyResponseDTO>> listarTodos() {
        return ResponseEntity.ok(
                hobbyService.listarTodos()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<HobbyResponseDTO> buscarPorId(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                hobbyService.buscarPorId(id)
        );
    }

    @PostMapping
    public ResponseEntity<HobbyResponseDTO> cadastrar(
            @RequestBody HobbyCreateRequestDTO dto
    ) {
        HobbyResponseDTO response = hobbyService.salvar(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HobbyResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody HobbyCreateRequestDTO dto
    ) {
        return ResponseEntity.ok(
                hobbyService.atualizar(id, dto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id
    ) {
        hobbyService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
