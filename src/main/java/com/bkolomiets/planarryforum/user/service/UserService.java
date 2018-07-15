package com.bkolomiets.planarryforum.user.service;

import com.bkolomiets.planarryforum.user.domain.User;
import com.bkolomiets.planarryforum.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Borislav Kolomiets
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
    private final IUserRepository userRepository;

    public void addUser(final User user) {
        List<User> users = userRepository.findAll();
        Stream<User> userStream = users.stream().filter(user1 -> user1.getUserName().equalsIgnoreCase(user.getUserName())
                                                              && user1.getPassword().equalsIgnoreCase(user.getPassword())
                                                              && user1.getEmail().equalsIgnoreCase(user.getEmail())
        );

        if (userStream.count() == 0) {
            userRepository.save(user);
        }
    }
}
