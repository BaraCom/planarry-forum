package com.bkolomiets.planarryforum.theme.controller;

import com.bkolomiets.planarryforum.theme.domain.Theme;
import com.bkolomiets.planarryforum.theme.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Borislav Kolomiets
 */
@Controller
@RequestMapping("/theme")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ThemeController {
    private final ThemeService themeService;

    @GetMapping("/all")
    public String all(final Model model) {
        List<Theme> allThemes = themeService.getAll();

        model.addAttribute("themes", allThemes);

        return "all-themes";
    }

    @GetMapping("/{title}")
    public String getThemeByTitle(@PathVariable("title") final String title, final Model model) {
        Theme theme = themeService.getByTitle(title);

        model.addAttribute("theme", theme);

        return "concrete-theme";
    }

    @GetMapping("/add")
    public String getAdd() {
        return "add-theme";
    }

    @PostMapping("/add")
    public String postAdd(@RequestParam(name = "title") final String title
                        , @RequestParam(name = "description") final String description) {
        themeService.addTheme(new Theme(title, description, LocalDate.now().toString()));

        return "redirect:/";
    }
}
