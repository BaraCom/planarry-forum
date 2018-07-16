package com.bkolomiets.planarryforum.comment.domain;

import com.bkolomiets.planarryforum.theme.domain.Theme;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

/**
 * @author Borislav Kolomiets
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {
    public Comment(Long levelId, String author, String date, String comment) {
        this.levelId = levelId;
        this.author = author;
        this.date = date;
        this.comment = comment;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "level_id")
    private Long levelId;

    @ManyToOne
    @JoinColumn(name = "theme_id")
    private Theme theme;

    @Column(name = "author")
    private String author;

    @Column(name = "date")
    private String date;

    @Column(name = "comment")
    private String comment;
}
