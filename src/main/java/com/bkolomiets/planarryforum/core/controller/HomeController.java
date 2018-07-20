package com.bkolomiets.planarryforum.core.controller;

import com.bkolomiets.planarryforum.core.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Borislav Kolomiets
 */
@Controller
@RequestMapping("/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HomeController {
    private final HomeService homeService;

    @GetMapping
    public String home() {
        return "redirect:/theme/all";
    }

    @GetMapping("/registration")
    public String registration(final Model model) {
        model.addAttribute("nav", homeService.getNavBarByRole());
        model.addAttribute("isLogged", homeService.getLogButtonByRole());

        return "registration";
    }

    @PostMapping("/registration")
    public String postRegistration(@RequestParam("loginReg") final String login
                                 , @RequestParam("passwordReg") final String password
                                 , @RequestParam("emailReg") final String email) {

        homeService.addUser(login, password, email);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(final Model model) {
        model.addAttribute("nav", homeService.getNavBarByRole());
        model.addAttribute("isLogged", homeService.getLogButtonByRole());

        return "login";
    }

    @GetMapping("/about")
    public String about(final Model model) {
        model.addAttribute("nav", homeService.getNavBarByRole());
        model.addAttribute("isLogged", homeService.getLogButtonByRole());

        return "about";
    }
}
