package com.mentor.blogStie.mapper;

import com.mentor.blogStie.dtos.BlogUserDto;
import com.mentor.blogStie.models.BlogUser;
import org.mapstruct.Mapper;

@Mapper
public interface BlogUserMapper {
    BlogUser toEntity(BlogUserDto blogUserDto);
    BlogUserDto toDto(BlogUser blogUser);
}
