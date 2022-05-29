package com.mentor.blogStie.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "blog_user")
@Getter
@Setter
public class BlogUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    @Column(unique = true)
    private String email;
    private String password;
    private String role;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<Post> posts ;

    public BlogUser() {
        this.posts = new ArrayList<>();
    }
}
