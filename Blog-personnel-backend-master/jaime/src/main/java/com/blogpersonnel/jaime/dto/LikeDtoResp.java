package com.blogpersonnel.jaime.dto;

import com.blogpersonnel.jaime.entites.Blog;
import com.blogpersonnel.jaime.entites.Client;
import lombok.*;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class LikeDtoResp {
    private int id;
    private Instant dateCreate;
    private Blog blog;
    private Client client;
}
