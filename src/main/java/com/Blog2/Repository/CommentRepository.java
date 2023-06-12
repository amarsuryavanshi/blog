package com.Blog2.Repository;

import com.Blog2.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {


    List<Comment> findByPostId(long postId);
}
