package com.prawly.posts;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prawly.exceptions.ResourceNotFoundException;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setDesc(postDTO.getDesc());
        post.setCreatedBy(1L);
        post.setCreatedDate(new Date());
        post.setUpdatedDate(new Date());
        return modelMapper.map(postRepository.save(post),PostDTO.class);
    }

    @Override
    public PostDTO updatePost(Long id, PostUpdateDTO updatePostDTO) {
        Post existingPost = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endpoint not found with id: " + id));
        modelMapper.map(updatePostDTO, existingPost);
        existingPost.setUpdatedDate(new Date());
        Post updatedPost = postRepository.save(existingPost);
        return modelMapper.map(updatedPost, PostDTO.class);
    }

    @Override
    public void deletePost(Long id) {
        if (!postRepository.existsById(id)) {
            throw new ResourceNotFoundException("Endpoint not found with id: " + id);
        }
        postRepository.deleteById(id);
    }

    @Override
    public List<PostDTO> getAllPosts() {
        return postRepository.findAll().stream().map(post -> modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toList());

    }

    @Override
    public PostDTO getPostbyId(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endpoint not found with id: " + id));
        return modelMapper.map(post, PostDTO.class);
    }

}
