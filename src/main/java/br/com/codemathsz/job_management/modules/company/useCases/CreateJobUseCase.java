package br.com.codemathsz.job_management.modules.company.useCases;

import br.com.codemathsz.job_management.exceptions.CompanyNotFoundException;
import br.com.codemathsz.job_management.modules.company.entities.CompanyEntity;
import br.com.codemathsz.job_management.modules.company.entities.JobEntity;
import br.com.codemathsz.job_management.modules.company.repositories.CompanyRepository;
import br.com.codemathsz.job_management.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository repository;

    @Autowired
    private CompanyRepository companyRepository;

    public ResponseEntity<Object> execute(JobEntity jobEntity){
        try{
            this.companyRepository.findById(jobEntity.getCompanyId()).orElseThrow(CompanyNotFoundException::new);
            this.repository.save(jobEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body(jobEntity) ;
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
