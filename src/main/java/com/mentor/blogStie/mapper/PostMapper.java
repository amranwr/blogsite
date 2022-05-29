package com.mentor.blogStie.mapper;

import com.mentor.blogStie.dtos.PostDto;
import com.mentor.blogStie.models.Post;
import org.mapstruct.Mapper;

@Mapper
public interface PostMapper {
    Post toEntity(PostDto postDto);
    PostDto toDto(Post post);
}
