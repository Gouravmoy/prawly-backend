package com.prawly.posts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {
		PostDTO newPost = postService.createPost(postDTO);
		return ResponseEntity.ok(newPost);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PostDTO> getPost(@PathVariable Long id) {
		PostDTO postDTO = postService.getPostbyId(id);
		return ResponseEntity.ok(postDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PostDTO> updatePost(@PathVariable Long id, @RequestBody PostUpdateDTO updatePostDTO) {
		PostDTO updateDto = postService.updatePost(id, updatePostDTO);
		return ResponseEntity.ok(updateDto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePost(@PathVariable Long id) {
		postService.deletePost(id);
		return ResponseEntity.ok("Post deleted successfully");
	}

	@GetMapping
	public ResponseEntity<List<PostDTO>> getPosts() {
		List<PostDTO> postDTOs = postService.getAllPosts();
		return ResponseEntity.ok(postDTOs);
	}

}
