package com.bkolomiets.planarryforum.core.service;

import com.bkolomiets.planarryforum.user.domain.User;
import com.bkolomiets.planarryforum.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
