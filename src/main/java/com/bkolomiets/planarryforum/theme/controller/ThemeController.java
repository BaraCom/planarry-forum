package com.bkolomiets.planarryforum.theme.controller;

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
@RequestMapping("/theme")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ThemeController {
    private final ThemeService themeService;
    private final HomeService homeService;
    private final CommentService commentService;

    @GetMapping("/all")
    public String all(final Model model) {
        List<Theme> allThemes = themeService.getAll();
        model.addAttribute("themes", allThemes);
        model.addAttribute("nav", homeService.getNavBarByRole());
        model.addAttribute("isLogged", homeService.getLogButtonByRole());

        return "all-themes";
    }

    @GetMapping("/{title}")
    public String getThemeByTitle(@PathVariable("title") final String title
                                                       , final Model model) {
        model.addAttribute("nav", homeService.getNavBarByRole());
        model.addAttribute("isLogged", homeService.getLogButtonByRole());

        Theme theme = themeService.getByTitle(title);
        model.addAttribute("theme", theme);

        List<Comment> comments = commentService.getByTheme(theme);
        model.addAttribute("comments", comments);

        return "concrete-theme";
    }

    @PostMapping("/{title}")
    public String addNewComment(@PathVariable("title") final String title
                              , @RequestParam("new-comment-text") final String newComment
                              , final Model model) {


        commentService.addNewComment(title, newComment);

        return getThemeByTitle(title, model);
    }

    @PostMapping("/{title}/{id}")
    public String addNewReplyComment(@PathVariable("title") final String title
                                   , @PathVariable("id") final String id
                                   , @RequestParam("new-reply-comment") final String newReplyComment
                                   , final Model model) {

        if (!newReplyComment.equals("")) {
            commentService.addNewReplyComment(title, newReplyComment, id);
        }

        return getThemeByTitle(title, model);
    }

    @GetMapping("/add")
    public String getAdd(final Model model) {
        model.addAttribute("nav", homeService.getNavBarByRole());
        model.addAttribute("isLogged", homeService.getLogButtonByRole());

        return "add-theme";
    }

    @PostMapping("/add")
    public String postAdd(@RequestParam(name = "title") final String title
                        , @RequestParam(name = "description") final String description) {
        themeService.addTheme(title, description);

        return "redirect:/theme/all";
    }
}
