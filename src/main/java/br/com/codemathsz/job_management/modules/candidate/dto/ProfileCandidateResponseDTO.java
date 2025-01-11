package br.com.codemathsz.job_management.modules.candidate.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ProfileCandidateResponseDTO(String description, String username, String email, UUID id, String name) {
}
