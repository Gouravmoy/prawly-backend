package com.prawly.posts;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostDTO {

    private Long id;
    private String title;
    private String desc;
    private Long createdBy;
    private Date createdDate;
    private Date updatedDate;

}
