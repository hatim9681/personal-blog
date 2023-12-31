package com.blogpersonnel.blogs.entites;

import lombok.*;

import java.time.Instant;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Jaime {
    private int id;
    private Instant dateCreate;
    private Blog blog;
    private Client client;
}
