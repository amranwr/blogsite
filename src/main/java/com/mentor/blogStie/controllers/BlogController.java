package com.mentor.blogStie.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BlogController.PATH)
public class BlogController {
    public static final String PATH = "/blog";

    @GetMapping("/v2")
    public String hello(){
        return "hello";
    }

    @GetMapping("/v1")
    public String hello2(){
        return "hello";
    }
}
