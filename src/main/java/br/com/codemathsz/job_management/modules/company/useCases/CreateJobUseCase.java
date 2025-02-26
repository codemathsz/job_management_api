package br.com.codemathsz.job_management.modules.company.useCases;

import br.com.codemathsz.job_management.exceptions.CompanyNotFoundException;
import br.com.codemathsz.job_management.modules.company.entities.JobEntity;
import br.com.codemathsz.job_management.modules.company.repositories.CompanyRepository;
import br.com.codemathsz.job_management.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository repository;

    @Autowired
    private CompanyRepository companyRepository;

    public JobEntity execute(JobEntity jobEntity){
        this.companyRepository.findById(jobEntity.getCompanyId()).orElseThrow(CompanyNotFoundException::new);
        return this.repository.save(jobEntity);
    }
}
