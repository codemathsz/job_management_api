package br.com.codemathsz.job_management.modules.candidate.useCases;

import br.com.codemathsz.job_management.modules.candidate.CandidateRepository;
import br.com.codemathsz.job_management.modules.candidate.dto.ProfileCanidateResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository repository;

    public ProfileCanidateResponseDTO execute(UUID candidateId){
        var candidate = this.repository.findById(candidateId).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return ProfileCanidateResponseDTO.builder()
                .id(candidate.getId())
                .name(candidate.getName())
                .username(candidate.getUsername())
                .email(candidate.getEmail())
                .description(candidate.getDescription())
                .build()
        ;
    }
}
