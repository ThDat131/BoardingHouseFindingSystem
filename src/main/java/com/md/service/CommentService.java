package com.md.service;

import com.md.pojo.Comment;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Map;

public interface CommentService {
    Comment addComment(Map<String, String> params, Principal user);
}
