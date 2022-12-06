package com.htecgroup.uber.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.htecgroup.uber.exception.login.IOErrorException;
import com.htecgroup.uber.exception.login.WrongPasswordException;
import com.htecgroup.uber.model.dto.LoggedUserDto;
import com.htecgroup.uber.model.jwt.JwtObject;
import com.htecgroup.uber.model.request.UserLoginRequest;
import com.htecgroup.uber.util.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@Log4j2
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final ObjectMapper objectMapper;

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String email = "";
        try {
            UserLoginRequest credentials =
                    objectMapper.readValue(request.getInputStream(), UserLoginRequest.class);
            UserDetails userDetails = userDetailsService.loadUserByUsername(credentials.getEmail());
            log.info("The user: {} is trying to authenticate.", userDetails.getUsername());
            email = userDetails.getUsername();

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userDetails.getUsername(), credentials.getPassword(), userDetails.getAuthorities()));

        } catch (BadCredentialsException ex) {
            throw new WrongPasswordException();
        } catch (IOException e) {
            log.error("Unable to authenticate, because of Input or Output error", e);
            throw new IOErrorException();
        }
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authentication)
            throws IOException, ServletException {

        LoggedUserDto user = (LoggedUserDto) authentication.getPrincipal();
        List<String> authorities = user.getRoleNames();

        JwtObject jwtObject = new JwtObject(user.getId(), user.getUsername());

        String token = JwtUtil.generateAccessToken(jwtObject, authorities);

        response.addHeader(JwtUtil.AUTH_HEADER, String.format("%s%s", JwtUtil.TOKEN_PREFIX, token));

        log.info("Jwt token successfully created for user: {}", user.getUsername());
        log.info("{} is successfully logged in.", user.getUsername());
    }
}
