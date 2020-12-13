package com.informatorio.myblog.controller;

import com.informatorio.myblog.model.Post;
import com.informatorio.myblog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping // al no especificar el request es igual al de request mapping
    public ResponseEntity<?> getPost(){
        return new ResponseEntity<>(postRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody Post post){
        return new ResponseEntity<>(postRepository.save(post), HttpStatus.CREATED);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<?> editPost(@PathVariable Long postId, @RequestBody Post post){
        Post postEdit = postRepository.getOne(postId);
        postEdit.setTitle(post.getTitle());
        return new ResponseEntity<>(postRepository.save(postEdit), HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId){
        Post postDelete = postRepository.getOne(postId);
        postRepository.delete(postDelete);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
