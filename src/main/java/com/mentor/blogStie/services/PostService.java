package com.mentor.blogStie.services;

import com.mentor.blogStie.dtos.PostDto;
import com.mentor.blogStie.exceptions.NotFoundException;
import com.mentor.blogStie.mapper.PostMapper;
import com.mentor.blogStie.models.BlogUser;
import com.mentor.blogStie.models.Post;
import com.mentor.blogStie.repositories.BlogUserRepo;
import com.mentor.blogStie.repositories.PostRepo;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PostService {
    private final PostMapper mapper;
    private final PostRepo postRepo;
    private final BlogUserRepo userRepo;
    private Authentication loggedInUser;
    public PostService(PostRepo postRepo, BlogUserRepo userRepo) {
        this.postRepo = postRepo;
        this.userRepo = userRepo;
        //  this.postRepo = postRepo;
        this.mapper = Mappers.getMapper(PostMapper.class);
        this.loggedInUser = SecurityContextHolder.getContext().getAuthentication();
    }


    public PostDto createNewPost(PostDto postDto) {
        this.loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        Post post = mapper.toEntity(postDto);
        Optional<BlogUser> optionalBlogUser = userRepo.findByEmail(loggedInUser.getName());
        if(optionalBlogUser.isPresent()){
            BlogUser user = optionalBlogUser.get();
            user.getPosts().add(post);
            post.setUser(user);
            userRepo.save(user);
            return postDto;
        }
        throw new UsernameNotFoundException("user not found : "+loggedInUser.getName());
    }

    public List<PostDto> getUserPosts() {
        this.loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        Optional<BlogUser> optionalBlogUser = userRepo.findByEmail(loggedInUser.getName());
        if(optionalBlogUser.isPresent())
            return optionalBlogUser.get().getPosts()
                    .stream().map(mapper::toDto)
                    .collect(Collectors.toList());
        throw new UsernameNotFoundException("user not found: "+loggedInUser.getName());
    }

    public List<PostDto> findAll(){
        return ((Collection<Post>) postRepo.findAll()).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id){
        postRepo.deleteById(id);
    }

    public PostDto updatePost(Long id ,PostDto postDto){
        Optional<Post> post = postRepo.findById(id);
        if(post.isPresent()) {
            post.get().setContent(postDto.getContent());
            post.get().setTitle(postDto.getTitle());
            return mapper.toDto(postRepo.save(post.get()));
        }
        throw new NotFoundException(id.toString());
    }
}
