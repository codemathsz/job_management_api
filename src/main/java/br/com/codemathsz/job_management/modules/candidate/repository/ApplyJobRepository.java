package br.com.codemathsz.job_management.modules.candidate.repository;

import br.com.codemathsz.job_management.modules.candidate.entity.ApplyJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplyJobRepository extends JpaRepository<ApplyJobEntity, UUID> {
}
