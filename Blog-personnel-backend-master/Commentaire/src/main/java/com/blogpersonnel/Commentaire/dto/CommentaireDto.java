package com.blogpersonnel.Commentaire.dto;

import com.blogpersonnel.Commentaire.entites.Blog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentaireDto {

    private String description;
    private int blog_id;
    private int client_id;

}
