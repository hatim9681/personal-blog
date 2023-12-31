package com.blogpersonnel.Commentaire.dto;

import com.blogpersonnel.Commentaire.entites.Blog;
import com.blogpersonnel.Commentaire.entites.Client;
import lombok.*;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CommentDtoRes {

    private int id;
    private String description;
    private Instant dateCreate;
    private Blog blog;
    private Client client;
}
