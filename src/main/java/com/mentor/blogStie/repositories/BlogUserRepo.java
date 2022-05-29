package com.mentor.blogStie.repositories;

import com.mentor.blogStie.models.BlogUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlogUserRepo extends CrudRepository<BlogUser,Long> {
    Optional<BlogUser> findByEmail(String email);
}
