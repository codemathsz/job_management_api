package br.com.codemathsz.job_management.modules.candidate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
public record AuthCandidateResponseDTO(String access_token) {
}
