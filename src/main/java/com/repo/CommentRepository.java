package com.repo;

import com.dom.Comment;
import com.dom.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    Optional<Comment> findByUsernameAndProduct(String username, Product product);
    List<Comment> findByProduct(Product product);
    Comment findFirstByOrderByIdDesc();
}
