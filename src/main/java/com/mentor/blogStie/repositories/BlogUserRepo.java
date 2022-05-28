package com.mentor.blogStie.repositories;

import com.mentor.blogStie.models.BlogUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BlogUserRepo extends CrudRepository<BlogUser,Long> {
    List<BlogUser> findByEmail(String email);
}
