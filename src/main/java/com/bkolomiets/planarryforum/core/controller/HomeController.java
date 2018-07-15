package com.bkolomiets.planarryforum.core.controller;

import com.bkolomiets.planarryforum.core.service.HomeService;
import com.bkolomiets.planarryforum.theme.service.ThemeService;
import com.bkolomiets.planarryforum.user.domain.User;
import com.bkolomiets.planarryforum.user.role.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Collections;

/**
 * @author Borislav Kolomiets
 */
@Controller
@RequestMapping("/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HomeController {
    private final HomeService homeService;
    private final ThemeService themeService;

    @GetMapping
    public String home() {
        return "redirect:/theme/all";
    }

    @GetMapping("/registration")
    public String registration(final Model model) {
        model.addAttribute("nav", themeService.getNavBarByRole());

        return "registration";
    }

    @PostMapping("/registration")
    public String postRegistration(@RequestParam("loginReg") final String login
                                 , @RequestParam("passwordReg") final String password
                                 , @RequestParam("emailReg") final String email) {
        User user = new User(login, password, email);
        user.setRoles(Collections.singleton(Role.USER));

        homeService.addUser(user);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(final Model model) {
        model.addAttribute("nav", themeService.getNavBarByRole());

        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@RequestParam("login") final String login
                          , @RequestParam("password") final String password) {

        return "redirect:/theme/all";
    }
}
