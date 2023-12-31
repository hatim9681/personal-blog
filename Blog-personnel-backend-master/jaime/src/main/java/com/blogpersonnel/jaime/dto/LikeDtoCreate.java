package com.blogpersonnel.jaime.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeDtoCreate {

    private int blog_id;
    private int client_id;
}
