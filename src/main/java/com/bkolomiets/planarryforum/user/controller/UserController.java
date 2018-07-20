package com.bkolomiets.planarryforum.user.controller;

import com.bkolomiets.planarryforum.core.service.HomeService;
import com.bkolomiets.planarryforum.user.role.Role;
import com.bkolomiets.planarryforum.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Borislav Kolomiets
 */
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final HomeService homeService;
    private final UserService userService;

    @GetMapping("/add")
    public String add(final Model model) {
        model.addAttribute("nav", homeService.getNavBarByRole());
        model.addAttribute("isLogged", homeService.getLogButtonByRole());
        model.addAttribute("roles", Role.values());

        return "user-add";
    }

    @PostMapping("/add")
    public String postAdd(@RequestParam("login") final String login
                        , @RequestParam("password") final String password
                        , @RequestParam("email") final String email
                        , @RequestParam("userRole") final String role) {
        userService.addUser(login, password, email, role);

        return "redirect:/";
    }

    @GetMapping("/update")
    public String getUpdate(final Model model) {
        model.addAttribute("nav", homeService.getNavBarByRole());
        model.addAttribute("isLogged", homeService.getLogButtonByRole());
        model.addAttribute("users", userService.getAllUsers());

        return "user-update";
    }

    @PostMapping("/update")
    public String postUpdate(@RequestParam("update-user-login") final String login
                           , @RequestParam("update-user-password") final String password
                           , @RequestParam("update-user-email") final String email
                           , @RequestParam("update-user-old-login") final String oldName
                           , @RequestParam("update-user-old-password") final String oldPassword) {
        userService.updateUser(login, password, email, oldName, oldPassword);

        return "redirect:/user/update";
    }

    @GetMapping("/delete")
    public String delete(final Model model) {
        model.addAttribute("nav", homeService.getNavBarByRole());
        model.addAttribute("isLogged", homeService.getLogButtonByRole());
        model.addAttribute("users", userService.getAllUsers());

        return "user-delete";
    }

    @PostMapping("/delete")
    public String postDelete(@RequestParam("id") final String userId) {
        userService.deleteUser(userId);

        return "redirect:/user/delete";
    }
}
