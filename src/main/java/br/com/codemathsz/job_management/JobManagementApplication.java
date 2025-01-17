package br.com.codemathsz.job_management;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Job Management",
				description = "API responsible for job management",
				version = "1"
		)
)
public class JobManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobManagementApplication.class, args);
	}

}
