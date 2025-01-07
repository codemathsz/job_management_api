package br.com.codemathsz.job_management.modules.company.repositories;

import br.com.codemathsz.job_management.modules.company.entities.CompanyEntity;
import br.com.codemathsz.job_management.modules.company.entities.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {


}