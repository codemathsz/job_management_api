package br.com.codemathsz.job_management.security;

import br.com.codemathsz.job_management.providers.JWTProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityCompanyFilter extends OncePerRequestFilter {

    @Autowired
    private JWTProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //SecurityContextHolder.getContext().setAuthentication(null); // clear
        String header = request.getHeader("Authorization");

        if(request.getRequestURI().startsWith("/company")) {
            if (header != null) {
                //valid token
                var token = this.jwtProvider.validateToken(header); // retrieve token of header
                if (token == null) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }

                var roles = token.getClaim("roles").asList(Object.class);
                var grants = roles.stream().map(
                        role -> new SimpleGrantedAuthority("ROLE_"+role.toString().toUpperCase())
                ).toList();
                request.setAttribute("company_id", token.getSubject());
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(token.getSubject(), null, grants);
                SecurityContextHolder.getContext().setAuthentication(auth); // inject auth spring security, because info users
            }
        }
        // valid route, verify if you need authorization
        filterChain.doFilter(request, response);
    }
}
