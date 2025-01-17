package br.com.codemathsz.job_management.modules.candidate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.UUID;

@Builder
public record ProfileCandidateResponseDTO(
        @Schema(example = "Desenvolvedor Java")
        String description,
        @Schema(example = "Matheus")
        String username,
        @Schema(example = "matheus@example.com")
        String email,
        UUID id,
        @Schema(example = "Matheus de Paula")
        String name
) {
}
