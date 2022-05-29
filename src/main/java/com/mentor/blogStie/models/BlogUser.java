package com.mentor.blogStie.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "blogUser")
@Getter
@Setter
public class BlogUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    private String email;
    private String password;
    private String role;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<Post> posts ;

    public BlogUser() {
        this.posts = new ArrayList<>();
    }
}
