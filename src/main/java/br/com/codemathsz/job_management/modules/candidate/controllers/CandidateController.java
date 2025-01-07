package br.com.codemathsz.job_management.modules.candidate.controllers;

import br.com.codemathsz.job_management.exceptions.UserFoundException;
import br.com.codemathsz.job_management.modules.candidate.CandidateEntity;
import br.com.codemathsz.job_management.modules.candidate.CandidateRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateRepository repository;

    @PostMapping("/")
    public CandidateEntity create(@Valid @RequestBody CandidateEntity candidateEntity){
        this.repository
        .findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
        .ifPresent((user) -> {
            throw new UserFoundException();
        });
        return this.repository.save(candidateEntity);
    }
}
