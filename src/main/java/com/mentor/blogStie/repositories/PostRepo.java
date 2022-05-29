package com.mentor.blogStie.repositories;

import com.mentor.blogStie.models.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PostRepo extends CrudRepository<Post,Long> {
    Optional<Post> findById(Long id);
}
