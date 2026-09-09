package com.springboot.prod_ready_features.service.impl;

import com.springboot.prod_ready_features.dtos.PostDTO;
import com.springboot.prod_ready_features.entities.PostEntity;
import com.springboot.prod_ready_features.entities.User;
import com.springboot.prod_ready_features.exceptions.ResourceNotFoundException;
import com.springboot.prod_ready_features.repositories.PostRepository;
import com.springboot.prod_ready_features.service.PostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class PostServiceImpl implements PostService {

    PostRepository postRepository;
    ModelMapper modelMapper;
    @Override
    public PostDTO getPostsById(Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("user : {}",user);
        PostEntity postEntity = postRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Post is not found with id : "+id));
        return modelMapper.map(postEntity,PostDTO.class);
    }

    @Override
    public List<PostDTO> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity,PostDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO createNewPosts(PostDTO postDTO) {
        return modelMapper.map(postRepository.save(modelMapper.map(postDTO,PostEntity.class)),PostDTO.class);
    }


}
