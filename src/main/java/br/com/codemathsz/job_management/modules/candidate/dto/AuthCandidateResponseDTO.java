package br.com.codemathsz.job_management.modules.candidate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
public record AuthCandidateResponseDTO(String access_token, Long expires_in) {
}
