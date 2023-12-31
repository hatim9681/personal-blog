package com.blogpersonnel.jaime.entites;

import lombok.*;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Blog {

    private int id;
    private String description;
    private String titre;
    private String imageUrl;
    private String imageId;
    private Instant dateCreate;

}
