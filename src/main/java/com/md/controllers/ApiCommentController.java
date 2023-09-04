package com.md.controllers;

import com.md.pojo.Comment;
import com.md.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiCommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/comment/")
    public ResponseEntity addComment(@RequestBody Map<String, String> params, Principal user){
        Comment comment = commentService.addComment(params, user);
        if (comment != null)
            return new ResponseEntity(comment, HttpStatus.CREATED);
        return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
    }
}
