package com.prawly.posts;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostUpdateDTO {

    private String title;
    private String desc;

}
