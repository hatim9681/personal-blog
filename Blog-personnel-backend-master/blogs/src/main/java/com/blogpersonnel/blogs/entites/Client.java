package com.blogpersonnel.blogs.entites;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    private int id;
    private String nom;
    private String telephone;
    private String email;
    private String image;

}