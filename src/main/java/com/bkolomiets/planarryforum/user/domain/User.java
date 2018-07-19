package com.bkolomiets.planarryforum.user.domain;

import com.bkolomiets.planarryforum.theme.domain.Theme;
import com.bkolomiets.planarryforum.user.role.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;

/**
 * @author Borislav Kolomiets
 */
@Entity
@Table(name = "usr")
@Getter
@Setter
@NoArgsConstructor
public class User {
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
}
