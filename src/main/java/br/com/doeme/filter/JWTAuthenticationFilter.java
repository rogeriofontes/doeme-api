package br.com.doeme.filter;

import br.com.doeme.user.service.TokenAuthenticationService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JWTAuthenticationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Authentication authetication = TokenAuthenticationService.getAuthetication((HttpServletRequest) servletRequest);

        SecurityContextHolder.getContext().setAuthentication(authetication);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
