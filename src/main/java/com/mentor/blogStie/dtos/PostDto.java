package com.mentor.blogStie.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mentor.blogStie.models.BlogUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PostDto {
    @Id
    private Long id ;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @JsonIgnore
    private BlogUser user;
}
