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
import static java.lang.Math.toIntExact;

/**
 * @author Borislav Kolomiets
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommentService {
    private final static Long DEPTH_NESTING = 1L;

    private final ICommentRepository commentRepository;
    private final ThemeService themeService;
    private final HomeService homeService;

    public void addNewComment(final String title, final String comment) {
        String date = LocalDate.now().toString();
        String name = homeService.getCurrentUserName();
        Theme theme = themeService.getThemeByTitle(title);

        Comment newComment = new Comment(0L, name, date, comment,null);
        newComment.setTheme(theme);

        commentRepository.save(newComment);
    }

    public void addNewReplyComment(final String title, final String comment, final String id) {
        String date = LocalDate.now().toString();
        String name = homeService.getCurrentUserName();
        Theme theme = themeService.getThemeByTitle(title);

        Comment commentById = commentRepository.findById(Long.valueOf(id)).get();
        Long levelId = commentById.getLevelId();

        Comment newComment = new Comment(++levelId, name, date, comment, Long.valueOf(id));
        newComment.setTheme(theme);

        if (newComment.getLevelId() <= DEPTH_NESTING) {
            commentRepository.save(newComment);
        }
    }

    public void saveEditComment(final String commentId, final String editComment) {
        Comment comment = commentRepository.findById(Long.valueOf(commentId)).get();
        comment.setComment(editComment);

        commentRepository.save(comment);
    }

    public void deleteComment(final String commentId) {
        Comment comment = commentRepository.findById(Long.valueOf(commentId)).get();

        commentRepository.delete(comment);
    }

    public List<Comment> getCommentsByTheme(final Theme theme) {
        List<Comment> byTheme = commentRepository.findByTheme(theme);
        List<Comment> sortedComments = new ArrayList<>();

        if (!byTheme.isEmpty()) {
            int maxNum = getMaxNum(byTheme);
            int minNum = getMinNum(byTheme);
            int count = minNum;

            for (int i = minNum; i < (maxNum + 1); i++) {
                int oldCount = count;
                for (Comment comment : byTheme) {

                    if (comment.getId() == count && comment.getLevelId() == 0L) {
                        sortedComments.add(comment);

                        sortedComments = getAllCommentChild(comment, byTheme, sortedComments);

                        count++;
                    }
                }
                if (count == oldCount) {
                    count++;
                }
            }
        }
        return sortedComments;
    }

    private int getMinNum(List<Comment> byTheme) {
        int minNum = toIntExact(byTheme.get(0).getId());

        for (Comment comment : byTheme) {
            if (comment.getId() < minNum) {
                minNum = toIntExact(comment.getId());
            }
        }
        return minNum;
    }

    private int getMaxNum(final List<Comment> allComments) {
        int maxNum = toIntExact(allComments.get(0).getId());

        for (Comment comment : allComments) {
            if (comment.getId() > maxNum) {
                maxNum = toIntExact(comment.getId());
            }
        }
        return maxNum;
    }

    private List<Comment> getAllCommentChild(final Comment comment
                                           , final List<Comment> allTheme
                                           , final List<Comment> sortedComments) {
        Long count1 = comment.getId();
        for (int j = 0; j < DEPTH_NESTING; j++) {
            for (Comment childComment : allTheme) {
                try {
                    if (childComment.getParentId().equals(count1)) {
                        count1 = childComment.getId();
                        sortedComments.add(childComment);
                    }
                } catch (NullPointerException e) {
                    e.getStackTrace();
                }
            }
        }
        return sortedComments;
    }
}
