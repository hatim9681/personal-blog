package com.blogpersonnel.Client.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientResponse {
    private int id;
    private String nom;
    private String telephone;
    private String email;
    private String image;

}
