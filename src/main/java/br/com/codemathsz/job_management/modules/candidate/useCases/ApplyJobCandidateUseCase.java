package br.com.codemathsz.job_management.modules.candidate.useCases;

import br.com.codemathsz.job_management.exceptions.JobNotFoundException;
import br.com.codemathsz.job_management.exceptions.UserNotFoundException;
import br.com.codemathsz.job_management.modules.candidate.CandidateRepository;
import br.com.codemathsz.job_management.modules.candidate.entity.ApplyJobEntity;
import br.com.codemathsz.job_management.modules.candidate.repository.ApplyJobRepository;
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

    @Autowired
    private ApplyJobRepository applyJobRepository;

    public ApplyJobEntity execute(UUID idCandidate, UUID idJob){
        this.candidateRepository.findById(idCandidate).orElseThrow(UserNotFoundException::new);
        this.jobRepository.findById(idJob).orElseThrow(JobNotFoundException::new);

        var applyJob = ApplyJobEntity.builder()
                .candidateId(idCandidate)
                .jobId(idJob)
                .build();
        return this.applyJobRepository.save(applyJob);
    }
}
