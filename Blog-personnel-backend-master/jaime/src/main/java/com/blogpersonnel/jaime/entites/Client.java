package com.blogpersonnel.jaime.entites;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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