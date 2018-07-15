package com.bkolomiets.planarryforum.comment.domain;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String userName;

    @Column(name = "date")
    private String date;

    @Column(name = "comment")
    private String comment;
}
