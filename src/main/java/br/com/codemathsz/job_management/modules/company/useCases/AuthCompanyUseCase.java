package br.com.codemathsz.job_management.modules.company.useCases;

import br.com.codemathsz.job_management.modules.company.dto.AuthCompanyDTO;
import br.com.codemathsz.job_management.modules.company.dto.AuthCompanyResponseDTO;
import br.com.codemathsz.job_management.modules.company.repositories.CompanyRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCompanyUseCase {

    @Value("${algorithm.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCompanyResponseDTO execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        var company = this.repository.findByUsername(authCompanyDTO.username()).orElseThrow(() -> new UsernameNotFoundException("Username/password incorrect"));

        // verify password
        var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.password(), company.getPassword());
        if(!passwordMatches){
            throw new AuthenticationException();
        }
        // generate jwt
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expireIn = Instant.now().plus(Duration.ofHours(2));

        var token = JWT.create().withIssuer("javagas")
                .withExpiresAt(expireIn)// get actual hour and add + 2hours
                .withSubject(company.getId().toString())
                .withClaim("roles", Arrays.asList("COMPANY"))
                .sign(algorithm);

        return AuthCompanyResponseDTO.builder()
                .access_token(token)
                .expire_in(expireIn.toEpochMilli())
                .build();
    }
}
