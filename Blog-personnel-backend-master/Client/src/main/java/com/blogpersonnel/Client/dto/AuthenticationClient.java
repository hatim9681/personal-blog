package com.blogpersonnel.Client.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationClient {

    private String email;
    private String password;
}
