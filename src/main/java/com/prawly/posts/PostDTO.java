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

    public static PostDTO maPostDTO (Post post){
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setDesc(post.getDesc());
        postDTO.setCreatedBy(post.getCreatedBy());
        postDTO.setCreatedDate(post.getCreatedDate());
        postDTO.setUpdatedDate(post.getUpdatedDate());
        return postDTO;
    }

}
