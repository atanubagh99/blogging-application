package com.example.blogging_application.service;


import com.example.blogging_application.bean.Post;
import com.example.blogging_application.dao.PostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostDAO postDAO;

    public Post createPost(Post post) {
        return postDAO.save(post);
    }

    public Optional<Post> getPostById(Long id) {
        //return postDAO.findById(id).orElseThrow(() -> new RuntimeException(id + " -> This id doesn't exist"));
        if (postDAO.findById(id).isPresent()) {
            return postDAO.findById(id);
        } else {
           throw  new RuntimeException(id + " -> This id doesn't exist");
        }
    }

    public List<Post> getAllPost(){
    return postDAO.findAll();
    }

    public Post updatePostById(Post post, Long id) {
        if (postDAO.findById(id).isPresent()) {
            Post newPost = new Post();
            newPost.setId(id);
            newPost.setDescription(post.getDescription());
            newPost.setTitle(post.getTitle());

            return postDAO.save(newPost);

        } else {
            throw  new RuntimeException(id + " -> This id doesn't exist");
        }
    }
}