package com.blogpersonnel.blogs.dto;

import com.blogpersonnel.blogs.entites.Client;
import com.blogpersonnel.blogs.entites.Commntaire;
import com.blogpersonnel.blogs.entites.Jaime;
import lombok.*;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Builder
public class BlogDto {

    private int id;
    private String description;
    private String titre;
    private String imageUrl;
    private String imageId;
    private Instant dateCreate;
    private Client client;
    private Commntaire[] commntaires;
    private Jaime[] jaimes;


}
