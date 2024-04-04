package com.prawly.posts;

import java.util.List;

public interface PostService {

    PostDTO createPost(PostDTO postDTO);

    PostDTO updatePost(Long id, PostDTO updatePostDTO);

    void deletePost(Long id);

    List<PostDTO> getAllPosts();

    PostDTO getPostbyId(Long id);
    
} 