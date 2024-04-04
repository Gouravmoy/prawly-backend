package com.prawly.posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
	public ResponseEntity<PostDTO> createEndpoint(@RequestBody PostDTO postDTO) {
		PostDTO newPost = postService.createPost(postDTO);
		return ResponseEntity.ok(newPost);
	}

}
