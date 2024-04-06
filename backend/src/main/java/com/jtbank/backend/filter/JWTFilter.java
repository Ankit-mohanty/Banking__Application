package com.jtbank.backend.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jtbank.backend.repository.AccountRepository;
import com.jtbank.backend.service.IJWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
    private final IJWTService service;
    private final ObjectMapper objectMapper;
    private final AccountRepository accountRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var path = request.getRequestURI();

        var passedPaths = List.of("login", "register", "create", "swagger", "api-doc",
                "/banking/api/v1/accounts/\\d+/image");

        for (var passedPath : passedPaths) {
            if (path.contains(passedPath) || path.matches(passedPath)) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        String token = request.getHeader("Authorization");

        try {
            if (token == null) {
                throw new RuntimeException("Token should not be empty");
            }

            String accountNumber = service.validateToken(token.substring(7));

            if (accountNumber == null || accountNumber.isEmpty() || accountNumber.isBlank())
                throw new RuntimeException("Token not found");

            var account = accountRepository.findByAccountNumber(Long.parseLong(accountNumber)).orElseThrow();
            var auth = new UsernamePasswordAuthenticationToken(account.getCredential().getAccountEmail(),
                    account.getCredential().getAccountPassword(), null);

            SecurityContextHolder.getContext().setAuthentication(auth);
            request.setAttribute("accountNumber", accountNumber);

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            var problemDetails = ProblemDetail.forStatus(400);
            problemDetails.setTitle("Token issue");
            problemDetails.setDetail(e.getMessage());

            response.setContentType("application/json");
            response.setStatus(400);
            response.getWriter().println(objectMapper.writeValueAsString(problemDetails));
        }
    }
}
