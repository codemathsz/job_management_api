package br.com.codemathsz.job_management.modules.company.useCases;

import br.com.codemathsz.job_management.exceptions.UserFoundException;
import br.com.codemathsz.job_management.modules.company.entities.CompanyEntity;
import br.com.codemathsz.job_management.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity execute(CompanyEntity companyEntity){
        this.repository
        .findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
        .ifPresent((user) -> {
            throw new UserFoundException();
        });
        // crypto password
        companyEntity.setPassword(passwordEncoder.encode(companyEntity.getPassword()));
        return this.repository.save(companyEntity);
    }
}
