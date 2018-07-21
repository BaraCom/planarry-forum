package com.bkolomiets.planarryforum.comment.controller;

import com.bkolomiets.planarryforum.comment.domain.Comment;
import com.bkolomiets.planarryforum.comment.service.CommentService;
import com.bkolomiets.planarryforum.core.service.HomeService;
import com.bkolomiets.planarryforum.theme.domain.Theme;
import com.bkolomiets.planarryforum.theme.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author Borislav Kolomiets
 */
@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommentController {
    private final CommentService commentService;
    private final ThemeService themeService;
    private final HomeService homeService;

    @GetMapping("/update")
    public String getUpdate(final Model model) {
        List<Theme> allThemes = themeService.getAll();
        model.addAttribute("themes", allThemes);
        model.addAttribute("nav", homeService.getNavBarByRole());
        model.addAttribute("isLogged", homeService.getLogButtonByRole());

        return "comment-update";
    }

    @GetMapping("/update/{title}")
    public String getUpdateByTitle(@PathVariable("title") final String title, final Model model) {
        model.addAttribute("nav", homeService.getNavBarByRole());
        model.addAttribute("isLogged", homeService.getLogButtonByRole());

        Theme theme = themeService.getThemeByTitle(title);
        model.addAttribute("theme", theme);

        List<Comment> comments = commentService.getCommentsByTheme(theme);
        model.addAttribute("comments", comments);

        return "concrete-comment-update";
    }

    @PostMapping("/update")
    public String postUpdate(@RequestParam("comment-update-id") final String commentId
                           , @RequestParam("edit-comment-form") final String editComment
                           , @RequestParam("comment-update-theme-title") final String themeTitle
                           , final Model model) {
        commentService.saveEditComment(commentId, editComment);

        return getUpdateByTitle(themeTitle, model);
    }

    @GetMapping("/delete")
    public String getDelete(final Model model) {
        List<Theme> allThemes = themeService.getAll();
        model.addAttribute("themes", allThemes);
        model.addAttribute("nav", homeService.getNavBarByRole());
        model.addAttribute("isLogged", homeService.getLogButtonByRole());

        return "comment-delete";
    }

    @GetMapping("/delete/{title}")
    public String getDeleteByTitle(@PathVariable("title") final String title, final Model model) {
        model.addAttribute("nav", homeService.getNavBarByRole());
        model.addAttribute("isLogged", homeService.getLogButtonByRole());

        Theme theme = themeService.getThemeByTitle(title);
        model.addAttribute("theme", theme);

        List<Comment> comments = commentService.getCommentsByTheme(theme);
        model.addAttribute("comments", comments);

        return "concrete-delete-comment";
    }

    @PostMapping("/delete")
    public String postDelete(@RequestParam("delete-comment-id") final String commentId
                           , @RequestParam("delete-comment-theme-title") final String themeTitle
                           , final Model model) {
        commentService.deleteComment(commentId);

        return getDeleteByTitle(themeTitle, model);
    }
}