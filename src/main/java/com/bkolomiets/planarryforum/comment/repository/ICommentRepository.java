package com.bkolomiets.planarryforum.comment.repository;

import com.bkolomiets.planarryforum.comment.domain.Comment;
import com.bkolomiets.planarryforum.theme.domain.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author Borislav Kolomiets
 */
@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByTheme(final Theme theme);
}
