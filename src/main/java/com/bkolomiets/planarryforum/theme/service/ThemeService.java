package com.bkolomiets.planarryforum.theme.service;

import com.bkolomiets.planarryforum.theme.domain.Theme;
import com.bkolomiets.planarryforum.theme.repository.IThemeRepository;
import com.bkolomiets.planarryforum.user.domain.User;
import com.bkolomiets.planarryforum.user.role.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

/**
 * @author Borislav Kolomiets
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ThemeService {
    private final IThemeRepository themeRepository;

    public List<Theme> getAll() {
        return themeRepository.findAll();
    }

    public Theme getByTitle(final String title) {
        return themeRepository.findByThemeTitle(title);
    }

    public void addTheme(final Theme theme) {
        themeRepository.save(theme);
    }

    public Map<String, String> getNavBarByRole() {
        if (isSameRoleName(Role.USER)) {
            return getUserNavBar();
        } else if (isSameRoleName(Role.ADMIN)) {
            return getAdminNavBar();
        }else {
            return getAnonymousNavBar();
        }
    }

    private Map<String, String> getUserNavBar() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("/", "Home");
        map.put("/theme/add", "Add theme");

        return map;
    }

    private Map<String, String> getAdminNavBar() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("Home", "/");
        map.put("/theme/add", "Add theme");
        map.put("/theme/update", "Edit theme");

        return map;
    }

    private Map<String, String> getAnonymousNavBar() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("", "Home");
        map.put("login", "Log in");

        return map;
    }

    private static boolean isSameRoleName(final Role role) {
        return getRoles().stream().anyMatch(a -> a.getAuthority().equals(role.name()));
    }

    private static Collection<? extends GrantedAuthority> getRoles() {
        return getContext().getAuthentication().getAuthorities();
    }
}
