package br.com.codemathsz.job_management.modules.company.controllers;

import br.com.codemathsz.job_management.modules.company.dto.CreateJobDTO;
import br.com.codemathsz.job_management.modules.company.entities.JobEntity;
import br.com.codemathsz.job_management.modules.company.useCases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/company/job")
public class JobController {

    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping("/")
    @PreAuthorize("hasRole('COMPANY')")// only user with COMPANY role
    public ResponseEntity<Object> create(@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request){
        var jobEntity = JobEntity.builder()
                .companyId(UUID.fromString(
                            request.getAttribute("company_id").toString()
                        )
                )
                .benefits(createJobDTO.benefits())
                .description(createJobDTO.description())
                .level(createJobDTO.level())
                .build(); // create instance of JobEntity
        return this.createJobUseCase.execute(jobEntity);
    }
}
