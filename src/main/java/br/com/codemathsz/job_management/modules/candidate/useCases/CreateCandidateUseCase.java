package br.com.codemathsz.job_management.modules.candidate.useCases;

import br.com.codemathsz.job_management.exceptions.UserFoundException;
import br.com.codemathsz.job_management.modules.candidate.CandidateEntity;
import br.com.codemathsz.job_management.modules.candidate.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidateRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CandidateEntity execute(CandidateEntity candidateEntity){
        this.repository
                .findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });

        candidateEntity.setPassword(passwordEncoder.encode(candidateEntity.getPassword()));

        return this.repository.save(candidateEntity);
    }
}
