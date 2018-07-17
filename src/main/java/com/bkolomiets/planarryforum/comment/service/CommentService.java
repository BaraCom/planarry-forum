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
import java.util.ArrayList;
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

    private final static Long DEPTH_NESTING = 1L;

    public void addNewComment(final String title, final String comment) {
        String date = LocalDate.now().toString();
        String name = homeService.getCurrentUserName();
        Theme theme = themeService.getByTitle(title);

        Comment newComment = new Comment(0L, name, date, comment,null);
        newComment.setTheme(theme);

        commentRepository.save(newComment);
    }

    public void addNewReplyComment(final String title, final String comment, final String id) {
        String date = LocalDate.now().toString();
        String name = homeService.getCurrentUserName();
        Theme theme = themeService.getByTitle(title);

        Comment commentById = commentRepository.findById(Long.valueOf(id)).get();
        Long levelId = commentById.getLevelId();

        Comment newComment = new Comment(++levelId, name, date, comment, Long.valueOf(id));
        newComment.setTheme(theme);

        if (newComment.getLevelId() <= DEPTH_NESTING) {
            commentRepository.save(newComment);
        }
    }

    public List<Comment> getByTheme(final Theme theme) {
        List<Comment> byTheme = commentRepository.findByTheme(theme);
        List<Comment> sortedComments = new ArrayList<>();

        for (Comment parentComment : byTheme) {
            if (parentComment.getLevelId() == 0L) {
                sortedComments.add(parentComment);
                Long count = parentComment.getId();

                for (int i = 0; i < DEPTH_NESTING; i++) {
                    for (Comment childComment : byTheme) {
                        if (childComment.getLevelId() == (i + 1) && childComment.getParentId().equals(count)) {
                            count = childComment.getId();
                            sortedComments.add(childComment);
                        }
                    }
                }
            }
        }
        return sortedComments;
    }
}
