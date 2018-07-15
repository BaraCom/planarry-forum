package com.bkolomiets.planarryforum.comment.repository;

import com.bkolomiets.planarryforum.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Borislav Kolomiets
 */
@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long> {

}
