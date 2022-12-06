package com.htecgroup.uber.filter;

import com.htecgroup.uber.exception.UberBaseException;
import com.htecgroup.uber.model.dto.LoggedUserDto;
import com.htecgroup.uber.model.response.ErrorMessage;
import com.htecgroup.uber.security.CustomAuthenticationToken;
import com.htecgroup.uber.security.CustomUserDetailsService;
import com.htecgroup.uber.util.DateTimeUtil;
import com.htecgroup.uber.util.ExceptionUtil;
import com.htecgroup.uber.util.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@AllArgsConstructor
public class CustomAuthorizationFilter extends OncePerRequestFilter {

    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            if (request
                    .getServletPath()
                    .startsWith(String.format("%s/", "/public"))) {
                filterChain.doFilter(request, response);
                return;
            }

            String authorizationHeader = request.getHeader(JwtUtil.AUTH_HEADER);
            if (authorizationHeader == null) {
                filterChain.doFilter(request, response);
                return;
            }

            if (!authorizationHeader.startsWith(JwtUtil.TOKEN_PREFIX)) {
                filterChain.doFilter(request, response);
                return;
            }

            String token = authorizationHeader.replace(JwtUtil.TOKEN_PREFIX, "");

            SecurityContextHolder.getContext().setAuthentication(JwtUtil.getFrom(token));
            UsernamePasswordAuthenticationToken authToken = JwtUtil.getFrom(token);

            LoggedUserDto loggedUserDto =
                    (LoggedUserDto)
                            customUserDetailsService.loadUserByUsername(authToken.getPrincipal().toString());

            CustomAuthenticationToken customAuthenticationToken =
                    new CustomAuthenticationToken(loggedUserDto, loggedUserDto.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(customAuthenticationToken);

            filterChain.doFilter(request, response);
        } catch (UberBaseException ex) {
            log.error(ex);
            ErrorMessage errorMessage =
                    new ErrorMessage(
                            ex.getMessage(), ex.getStatus().value(), DateTimeUtil.currentTimeFormatted());
            ExceptionUtil.writeToResponse(errorMessage, response);
        } catch (Exception ex) {
            log.error(ex);

            List<String> messages = new ArrayList<>();
            Throwable currentException = ex;
            while (currentException != null && currentException != currentException.getCause()) {
                messages.add(currentException.getLocalizedMessage());
                currentException = currentException.getCause();
            }

            ErrorMessage errorMessage =
                    new ErrorMessage(
                            messages,
                            HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            DateTimeUtil.currentTimeFormatted());

            ExceptionUtil.writeToResponse(errorMessage, response);
        }
    }
}
