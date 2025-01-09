package br.com.codemathsz.job_management.modules.candidate.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ProfileCanidateResponseDTO(String description, String username, String email,UUID id, String name) {
}
