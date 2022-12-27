//package com.project.green.security;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//
//
//public class CustomUserDetails implements UserDetails {
//
//    private final int id;
//    private final String email;
//    private final String password;
//    private final Collection<CustomGrantedAuthority> getAuthorities;
//
//    public CustomUserDetails(int id, String email, String password, Collection<CustomGrantedAuthority> getAuthorities) {
//        this.id = id;
//        this.email = email;
//        this.password = password;
//        this.getAuthorities = getAuthorities;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return getAuthorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return email;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//    public int getId() {
//        return id;
//    }
//}
