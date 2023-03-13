package s9.apr.giftservices.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtLogoutFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (isLogoutRequest(request)) {
            //tokenProvider.invalidateToken(request);
            clearAuthentication(request);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Logged out successfully");
            response.getWriter().flush();
            response.getWriter().close();
            return;
        }
        filterChain.doFilter(request, response);
    }
    private boolean isLogoutRequest(HttpServletRequest request) {
        return request.getMethod().equals("POST") && request.getServletPath().equals("/logout");
    }

    private void clearAuthentication(HttpServletRequest request) {
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
