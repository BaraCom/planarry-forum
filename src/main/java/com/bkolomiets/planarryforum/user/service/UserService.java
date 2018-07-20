package com.bkolomiets.planarryforum.user.service;

import com.bkolomiets.planarryforum.theme.domain.Theme;
import com.bkolomiets.planarryforum.user.domain.User;
import com.bkolomiets.planarryforum.user.repository.IUserRepository;
import com.bkolomiets.planarryforum.user.role.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Math.toIntExact;
import static java.util.Collections.singleton;

/**
 * @author Borislav Kolomiets
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
    private final IUserRepository userRepository;

    public void addUser(final String login, final String password, final String email, final String role) {
        User user = new User(login, password, email);
        user.setActive(true);
        user.setRoles(singleton(Role.valueOf(role)));

        List<User> users = userRepository.findAll();
        Stream<User> userStream = users.stream().filter(user1 -> user1.getUsername().equalsIgnoreCase(user.getUsername())
                                                              && user1.getPassword().equalsIgnoreCase(user.getPassword())
                                                              && user1.getEmail().equalsIgnoreCase(user.getEmail())
        );

        if (userStream.count() == 0) {
            userRepository.save(user);
        }
    }

    public List<User> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        List<User> sortedList = new ArrayList<>();
        int minNum = getMinNum(allUsers);
        int maxNum = getMaxNum(allUsers);
        int count = minNum;

        for (int i = minNum; i < (maxNum + 1); i++) {
            int oldCount = count;

            for (User user : allUsers) {
                if (user.getId() == count) {
                    sortedList.add(user);
                    count++;
                }
            }
            if (count == oldCount) {
                count++;
            }
        }
        return sortedList;
    }

    public void updateUser(final String login, final String password, final String email, final String oldName, final String oldPassword) {
        User user = userRepository.findByUsernameAndPassword(oldName, oldPassword);
        user.setUsername(login);
        user.setPassword(password);
        user.setEmail(email);

        userRepository.save(user);
    }

    public void deleteUser(final String userId) {
        User user = userRepository.getOne(Long.valueOf(userId));

        userRepository.delete(user);
    }

    private int getMinNum(List<User> allUsers) {
        int minNum = toIntExact(allUsers.get(0).getId());

        for (User user : allUsers) {
            if (user.getId() < minNum) {
                minNum = toIntExact(user.getId());
            }
        }
        return minNum;
    }

    private int getMaxNum(List<User> allUsers) {
        int maxNum = toIntExact(allUsers.get(0).getId());

        for (User user : allUsers) {
            if (user.getId() > maxNum) {
                maxNum = toIntExact(user.getId());
            }
        }
        return maxNum;
    }
}
