package com.mentor.blogStie.services;

import com.mentor.blogStie.models.BlogUser;
import com.mentor.blogStie.models.SecurityUser;
import com.mentor.blogStie.repositories.BlogUserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BlogUserDetailsService implements UserDetailsService {
    private final BlogUserRepo repo;

    public BlogUserDetailsService(BlogUserRepo repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<BlogUser> users = repo.findByEmail(username);
        if(users.size() == 0 ){
            throw new UsernameNotFoundException(username);
        }
        return new SecurityUser(users.get(0));
    }
}
