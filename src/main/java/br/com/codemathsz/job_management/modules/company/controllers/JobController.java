package br.com.codemathsz.job_management.modules.company.controllers;

import br.com.codemathsz.job_management.modules.company.dto.CreateJobDTO;
import br.com.codemathsz.job_management.modules.company.entities.JobEntity;
import br.com.codemathsz.job_management.modules.company.useCases.CreateJobUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    @Tag(name = "Vagas", description = "Informações das vagas")
    @Operation(summary = "Cadastro de vaga", description = "Essa função é responsável por cadastrar novas vagas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(
                        schema = @Schema(
                                implementation = JobEntity.class
                        )
                    )
            })
    })
    @SecurityRequirement(name = "jwt_auth")
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
        try{

            return ResponseEntity.status(HttpStatus.CREATED).body(this.createJobUseCase.execute(jobEntity));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
