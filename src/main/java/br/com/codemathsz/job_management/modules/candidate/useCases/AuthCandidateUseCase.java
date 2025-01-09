package br.com.codemathsz.job_management.modules.candidate.useCases;

import br.com.codemathsz.job_management.modules.candidate.dto.AuthCandidateDTO;
import br.com.codemathsz.job_management.modules.candidate.dto.AuthCandidateResponseDTO;
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
import java.util.List;

@Service
public class AuthCandidateUseCase {

    @Value("${algorithm.token.secret.candidate}")
    private String secretKey;

    @Autowired
    private CompanyRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCandidateResponseDTO execute(AuthCandidateDTO authCandidateDTO) throws AuthenticationException {
        var candidate =this.repository.findByUsername(authCandidateDTO.username())
                .orElseThrow(() -> new UsernameNotFoundException("Username/password incorrect"))
        ;

        var passwordMatches = this.passwordEncoder.matches(authCandidateDTO.password(), candidate.getPassword());

        if(!passwordMatches){
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var token = JWT.create()
                .withIssuer("javagas")
                .withSubject(candidate.getId().toString())
                .withClaim("roles", List.of("candidate")) // types of claims/demands
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .sign(algorithm)
        ;

        return AuthCandidateResponseDTO.builder()
        .access_token(token)
        .build();
    }
}
