package com.blogpersonnel.Client.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterClient {

    private String nom;
    private String telephone;
    private String email;
    private String image;

    private String password;
    private String confirm_password;
}
