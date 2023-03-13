package s9.apr.giftservices.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.persistence.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import s9.apr.giftservices.entities.UserPrincipal;
import s9.apr.giftservices.services.TutorDetailsServiceImpl;
import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired private TutorDetailsServiceImpl tutorDetailsService;
    @Autowired private JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);
            try {
                String email = jwtUtil.validateTokenAndRetrieveSubject(jwt);
                UserPrincipal userDetails = (UserPrincipal) tutorDetailsService.loadUserByUsername(email);
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authToken);
            } catch (JWTVerificationException | EntityNotFoundException exc) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
