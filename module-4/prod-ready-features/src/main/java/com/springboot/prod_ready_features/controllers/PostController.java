package com.springboot.prod_ready_features.controllers;

import com.springboot.prod_ready_features.dtos.PostDTO;
import com.springboot.prod_ready_features.service.PostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class PostController {
    PostService postService;

    @GetMapping(path = "/{postId}")
    public PostDTO getPostsById(@PathVariable Long postId)
    {
        return postService.getPostsById(postId);
    }

    @GetMapping
    public List<PostDTO> getAllPosts()
    {
        return postService.getAllPosts();
    }

    @PostMapping
    public PostDTO createNewPosts(@RequestBody PostDTO postDTO)
    {
        return postService.createNewPosts(postDTO);
    }

}
