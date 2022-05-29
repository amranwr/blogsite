package com.mentor.blogStie.controllers;

import com.mentor.blogStie.dtos.BlogUserDto;
import com.mentor.blogStie.services.BlogUserDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@RestController
@RequestMapping(BlogUserController.PATH)
public class BlogUserController {
    public static final String PATH = "/blog";
    private final BlogUserDetailsService service;

    public BlogUserController(BlogUserDetailsService service) {
        this.service = service;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public BlogUserDto hello(@Validated @RequestBody  BlogUserDto blogUserDto){
        return service.register(blogUserDto);
    }


    @GetMapping("/logout")
    public RedirectView logout(){
        return new RedirectView("/login");
    }

    @PutMapping("/user/update")
    public BlogUserDto updateUser(@Validated @RequestBody  BlogUserDto blogUserDto){
        return service.updateUser(blogUserDto);
    }
}
