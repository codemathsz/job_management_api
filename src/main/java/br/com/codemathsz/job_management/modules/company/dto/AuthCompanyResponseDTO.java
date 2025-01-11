package br.com.codemathsz.job_management.modules.company.dto;

import lombok.Builder;

@Builder
public record AuthCompanyResponseDTO(String access_token, Long expire_in) {
}
