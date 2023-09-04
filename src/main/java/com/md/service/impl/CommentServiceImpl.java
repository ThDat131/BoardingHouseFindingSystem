package com.md.service.impl;

import com.md.pojo.Comment;
import com.md.pojo.Post;
import com.md.pojo.User;
import com.md.repository.CommentRepository;
import com.md.repository.PostRepository;
import com.md.repository.UserRepository;
import com.md.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Override
    public Comment addComment(Map<String, String> params, Principal user) {
        try {
            Comment comment = new Comment();
            String id = String.valueOf(UUID.randomUUID());
            String content = params.get("content");
            String postId = params.get("postId");
            User u = userRepository.getUserByUsername(user.getName());
            Post p = postRepository.getPostById(postId);

            comment.setId(id);
            comment.setContent(content);
            comment.setPostId(p);
            comment.setUsername(u);
            commentRepository.addComment(comment);
            return comment;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
