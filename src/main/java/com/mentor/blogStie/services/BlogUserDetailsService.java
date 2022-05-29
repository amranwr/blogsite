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

import java.util.Optional;

@Service
public class BlogUserDetailsService implements UserDetailsService {
    private final BlogUserRepo repo;
    private final PasswordEncoder passwordEncoder;
    private final BlogUserMapper mapper;
    public BlogUserDetailsService(BlogUserRepo repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
        this.mapper = Mappers.getMapper(BlogUserMapper.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<BlogUser> user = repo.findByEmail(username);

        if(user.isPresent())
            return new SecurityUser(user.get());

        throw new UsernameNotFoundException(username);
    }

    public BlogUserDto register(BlogUserDto blogUserDto) {
        Optional<BlogUser> optionalBlogUser = repo.findByEmail(blogUserDto.getEmail());

        if (optionalBlogUser.isPresent()) throw new UserExists(blogUserDto.getEmail());

        BlogUser user = mapper.toEntity(blogUserDto);
        user.setPassword(passwordEncoder.encode(blogUserDto.getPassword()));

        return mapper.toDto(repo.save(user));

    }


    public BlogUserDto updateUser(BlogUserDto blogUserDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Optional<BlogUser> optionalBlogUser = repo.findByEmail(blogUserDto.getEmail());
        Optional<BlogUser> actualUser = repo.findByEmail(auth.getName());

        if(optionalBlogUser.isPresent()) throw new UserExists(blogUserDto.getEmail());

        BlogUser user = actualUser.get();
        user.setPassword(passwordEncoder.encode(blogUserDto.getPassword()));
        user.setRole(blogUserDto.getRole());

        return mapper.toDto(repo.save(user));
    }
}
