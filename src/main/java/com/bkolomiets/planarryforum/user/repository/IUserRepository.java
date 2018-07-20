package com.bkolomiets.planarryforum.user.repository;

import com.bkolomiets.planarryforum.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Borislav Kolomiets
 */
@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    User findByUsername(final String name);

    User findByUsernameAndPassword(final String username, final String password);
}
