package com.bkolomiets.planarryforum.core.service;

import com.bkolomiets.planarryforum.user.domain.User;
import com.bkolomiets.planarryforum.user.role.Role;
import com.bkolomiets.planarryforum.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import static org.springframework.security.core.context.SecurityContextHolder.getContext;

/**
 * @author Borislav Kolomiets
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HomeService {
    private final UserService userService;

    public void addUser(final User user) {
        userService.addUser(user);
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

    public boolean getLogButtonByRole() {
        return isSameRoleName(Role.USER) || isSameRoleName(Role.ADMIN);
    }

    public String getCurrentUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return auth.getName();
    }

    private Map<String, String> getUserNavBar() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("/", "Home");
        map.put("/theme/add", "Add theme");

        return map;
    }

    private Map<String, String> getAdminNavBar() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("/", "Home");
        map.put("/theme/add", "Add theme");
        map.put("/theme/update", "Edit theme");

        return map;
    }

    private Map<String, String> getAnonymousNavBar() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("/", "Home");

        return map;
    }

    private boolean isSameRoleName(final Role role) {
        return getRoles().stream().anyMatch(a -> a.getAuthority().equals(role.name()));
    }

    private Collection<? extends GrantedAuthority> getRoles() {
        return getContext().getAuthentication().getAuthorities();
    }
}
