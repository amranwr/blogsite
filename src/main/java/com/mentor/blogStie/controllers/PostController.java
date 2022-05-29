package com.mentor.blogStie.controllers;


import com.mentor.blogStie.dtos.PostDto;
import com.mentor.blogStie.services.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class PostController {
    private final PostService service;
    public PostController(PostService service) {
        this.service = service;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto createNewPost(@RequestBody @Valid PostDto postDto){
        return service.createNewPost(postDto);
    }

    @PutMapping("/{id}")
    public PostDto updatePost(@PathVariable Long id,@RequestBody @Valid PostDto postDto){
        return service.updatePost(id,postDto);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id){
        service.deleteById(id);
    }

    @GetMapping("/profile")
    public List<PostDto> getUserPosts(){
        return service.getUserPosts();
    }

    @GetMapping
    public List<PostDto> findAll(){
        return service.findAll();
    }

}
