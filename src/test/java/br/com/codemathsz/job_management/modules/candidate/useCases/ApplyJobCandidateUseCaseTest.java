package br.com.codemathsz.job_management.modules.candidate.useCases;

import br.com.codemathsz.job_management.exceptions.JobNotFoundException;
import br.com.codemathsz.job_management.exceptions.UserNotFoundException;
import br.com.codemathsz.job_management.modules.candidate.CandidateEntity;
import br.com.codemathsz.job_management.modules.candidate.CandidateRepository;
import br.com.codemathsz.job_management.modules.company.repositories.JobRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

    @InjectMocks
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Test
    @DisplayName("Should not be able to apply job with candidate not found")
    public void should_not_be_able_to_apply_job_with_candidate_not_found(){
        try{
            applyJobCandidateUseCase.execute(null, null);
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(UserNotFoundException.class);
        }
    }

    @Test
    @DisplayName("Should not be able to apply job with job not found")
    public void should_not_be_able_to_apply_job_with_job_not_found(){
        var idCandidate = UUID.randomUUID();
        var candidate = new CandidateEntity();
        candidate.setId(idCandidate);
        Mockito.when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));
        try{
            applyJobCandidateUseCase.execute(idCandidate, null);
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(JobNotFoundException.class);
        }
    }
}
