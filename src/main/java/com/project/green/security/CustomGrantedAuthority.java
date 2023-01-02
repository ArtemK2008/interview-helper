package com.project.green.security;

import com.project.green.entities.Role;
import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;

public class CustomGrantedAuthority implements GrantedAuthority {

    private final String PREFIX = "ROLE_";

    private final Role userRole;

    public CustomGrantedAuthority(Role userRole) {
        this.userRole = userRole;
    }

    @Override
    public String getAuthority() {
        return PREFIX + userRole.getPosition();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomGrantedAuthority)) return false;
        CustomGrantedAuthority that = (CustomGrantedAuthority) o;
        return userRole == that.userRole;
    }

    @Override
    public int hashCode() {
        return Objects.hash(PREFIX, userRole);
    }
}
