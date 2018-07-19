package com.bkolomiets.planarryforum.theme.domain;

import com.bkolomiets.planarryforum.comment.domain.Comment;
import com.bkolomiets.planarryforum.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Borislav Kolomiets
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Theme {
    public Theme(String themeTitle, String description, String creationDate) {
        this.themeTitle = themeTitle;
        this.description = description;
        this.creationDate = creationDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "theme_title")
    private String themeTitle;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private String creationDate;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "theme", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();
}
