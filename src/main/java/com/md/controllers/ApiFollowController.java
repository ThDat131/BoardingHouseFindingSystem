package com.md.controllers;

import com.md.pojo.Follow;
import com.md.service.CommentService;
import com.md.service.FollowService;
import com.md.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiFollowController {
    @Autowired
    private FollowService followService;
    @Autowired
    private UserService userService;

    @PostMapping("/follow/{landLordId}/")
    public ResponseEntity addFollow(@PathVariable(value = "landLordId") String landLordId, Principal user){
        Follow follow = followService.addFollow(landLordId, user);
        if (follow != null)
            return new ResponseEntity(follow, HttpStatus.CREATED);
        return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/follow/{landLordId}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFollow(@PathVariable(value = "landLordId") String landLordId, Principal user) {
        String tentantId = this.userService.getUserByUsername(user.getName()).getTentant().getId();
        this.followService.deleteFollow(landLordId, tentantId);
    }
}
