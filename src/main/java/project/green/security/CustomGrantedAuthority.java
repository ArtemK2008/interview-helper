package project.green.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;

public class CustomGrantedAuthority implements GrantedAuthority {

    private final String PREFIX = "ROLE_";
    private final UserRole userRole;

    public CustomGrantedAuthority(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public String getAuthority() {
        return PREFIX + userRole.name();
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
