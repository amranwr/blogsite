package com.mentor.blogStie.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BlogUserDto {

    private int id ;
    @NotBlank
    private String email;
    @NotBlank
    @Size(min = 5)
    private String password;
    @NotBlank
    private String role;
}
