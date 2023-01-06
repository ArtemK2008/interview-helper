package com.project.green.security;

import com.project.green.dao.PersonDao;
import com.project.green.entities.Person;
import com.project.green.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private PersonDao personDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Person person = personDao.getPersonByEmail(email).orElseThrow(() -> new EntityNotFoundException("Entity not found."));
        if (person == null) {
            throw new UsernameNotFoundException("User not found.");
        }
        return new CustomUserDetails(
                person.getId(),
                person.getEmail(),
                person.getPassword(),
                person.getRoles()
                        .stream().map(CustomGrantedAuthority::new).collect(Collectors.toList()));
    }
}
