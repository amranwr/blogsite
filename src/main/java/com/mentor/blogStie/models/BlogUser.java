package com.mentor.blogStie.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "blogUser")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id ;

    private String email;
    private String password;
    private String role;
}
