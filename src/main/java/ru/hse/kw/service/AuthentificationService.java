package ru.hse.kw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.hse.kw.dao.UserDao;
import ru.hse.kw.service.UserService;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

@Service
public class AuthentificationService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        ru.hse.kw.model.User user = userDao.findByLogin(username);

        GrantedAuthority authority = new SimpleGrantedAuthority("USER");
        UserDetails userDetails = (UserDetails)new org.springframework.security.core.userdetails.User(user.getLogin(),
                user.getUsersPassword().getPassword(), Arrays.asList(authority));
        return userDetails;
    }
}