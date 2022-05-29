package com.mentor.blogStie.services;

import com.mentor.blogStie.dtos.BlogUserDto;
import com.mentor.blogStie.exceptions.UserExists;
import com.mentor.blogStie.mapper.BlogUserMapper;
import com.mentor.blogStie.models.BlogUser;
import com.mentor.blogStie.models.SecurityUser;
import com.mentor.blogStie.repositories.BlogUserRepo;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BlogUserDetailsService implements UserDetailsService {
    private final BlogUserRepo repo;
    private final PasswordEncoder passwordEncoder;
    private final BlogUserMapper mapper;
    private Authentication auth;
    public BlogUserDetailsService(BlogUserRepo repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
        this.mapper = Mappers.getMapper(BlogUserMapper.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<BlogUser> users = repo.findByEmail(username);
        if(users.size() == 0 ){
            throw new UsernameNotFoundException(username);
        }
        return new SecurityUser(users.get(0));
    }

    public BlogUserDto register(BlogUserDto blogUserDto) {
        List<BlogUser> users = repo.findByEmail(blogUserDto.getEmail());
        if(users.size() >  0)
            throw new UserExists(blogUserDto.getEmail());
        BlogUser user = mapper.toEntity(blogUserDto);
        user.setPassword(passwordEncoder.encode(blogUserDto.getPassword()));
        return mapper.toDto(repo.save(user));
    }


    /*public BlogUserDto updateUser(BlogUserDto blogUserDto) {
        this.auth = SecurityContextHolder.getContext().getAuthentication();


    }*/
}
