package br.com.codemathsz.job_management.modules.candidate.useCases;

import br.com.codemathsz.job_management.modules.candidate.CandidateRepository;
import br.com.codemathsz.job_management.modules.candidate.dto.ProfileCandidateResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository repository;

    public ProfileCandidateResponseDTO execute(UUID candidateId){
        var candidate = this.repository.findById(candidateId).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return ProfileCandidateResponseDTO.builder()
                .description(candidate.getDescription())
                .username(candidate.getUsername())
                .email(candidate.getEmail())
                .name(candidate.getName())
                .id(candidate.getId())
                .build();
    }
}
