package com.htecgroup.uber.model.dto;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
public class LoggedUserDto extends User {
    private UUID id;
    private boolean isActive;

    public LoggedUserDto(
            UUID id,
            String email,
            String password,
            boolean enabled,
            boolean accountNonExpired,
            boolean credentialsNonExpired,
            boolean accountNonLocked,
            Collection<? extends GrantedAuthority> authorities) {
        super(
                email,
                password,
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                authorities);
        this.id = id;
    }

    public boolean hasRole(String roleName) {
        return this.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(e -> e.equals(roleName));
    }

    public List<String> getRoleNames() {
        return super.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }
}
