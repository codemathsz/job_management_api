package br.com.codemathsz.job_management.modules.candidate.useCases;

import br.com.codemathsz.job_management.exceptions.JobNotFoundException;
import br.com.codemathsz.job_management.exceptions.UserNotFoundException;
import br.com.codemathsz.job_management.modules.candidate.CandidateRepository;
import br.com.codemathsz.job_management.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplyJobCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    public void execute(UUID idCandidate, UUID idJob){
        this.candidateRepository.findById(idCandidate).orElseThrow(UserNotFoundException::new);
        this.jobRepository.findById(idJob).orElseThrow(JobNotFoundException::new);
    }
}
