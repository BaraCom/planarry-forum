package com.bkolomiets.planarryforum.comment.service;

import com.bkolomiets.planarryforum.comment.domain.Comment;
import com.bkolomiets.planarryforum.comment.repository.ICommentRepository;
import com.bkolomiets.planarryforum.core.service.HomeService;
import com.bkolomiets.planarryforum.theme.domain.Theme;
import com.bkolomiets.planarryforum.theme.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Borislav Kolomiets
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommentService {
    private final ICommentRepository commentRepository;
    private final ThemeService themeService;
    private final HomeService homeService;

    public void addNewComment(final String title, final String comment) {
        String date = LocalDate.now().toString();
        String name = homeService.getCurrentUserName();
        Theme theme = themeService.getByTitle(title);

        Comment newComment = new Comment(0L, name, date, comment);
        newComment.setTheme(theme);

        commentRepository.save(newComment);
    }

    public List<Comment> getByTheme(final Theme theme) {

        return commentRepository.findByTheme(theme);
    }
}
