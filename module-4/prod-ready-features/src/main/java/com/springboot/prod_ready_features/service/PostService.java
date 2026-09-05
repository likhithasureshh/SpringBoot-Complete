package com.springboot.prod_ready_features.service;

import com.springboot.prod_ready_features.dtos.PostDTO;

import java.util.List;

public interface PostService {
    PostDTO getPostsById(Long id);

    List<PostDTO> getAllPosts();

    PostDTO createNewPosts(PostDTO postDTO);
}
