package dev.jamal.projetotcc.Controllers;

import dev.jamal.projetotcc.DTO.Feedback.FeedbackCreateRequestDTO;
import dev.jamal.projetotcc.DTO.Feedback.FeedbackResponseDTO;
import dev.jamal.projetotcc.Service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feedbacks")
@RequiredArgsConstructor
@CrossOrigin("*")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<FeedbackResponseDTO> avaliar(
            @RequestBody FeedbackCreateRequestDTO dto
    ) {
        FeedbackResponseDTO response = feedbackService.avaliar(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FeedbackResponseDTO>> listarPorUsuario(
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(
                feedbackService.listarPorUsuario(userId)
        );
    }
}
