package com.blogpersonnel.blogs.entites;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Commntaire {

    private int id;
    private String description;
    private Instant dateCreate;
    private Blog blog;
    private Client client;

}
