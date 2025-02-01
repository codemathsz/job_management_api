package br.com.codemathsz.job_management.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record CreateJobDTO(
        @Schema(example = "Vaga para pessoa Desenvolvedora Junior", requiredMode = Schema.RequiredMode.REQUIRED)
        String description,
        @Schema(example = "GYMPass, Plano de saúde", requiredMode = Schema.RequiredMode.REQUIRED)
        String benefits,
        @Schema(example = "JUNIOR", requiredMode = Schema.RequiredMode.REQUIRED)
        String level
) {
}