package com.blogpersonnel.jaime.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Jaime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Instant dateCreate;
    private int blog_id;
    private int client_id;
}
