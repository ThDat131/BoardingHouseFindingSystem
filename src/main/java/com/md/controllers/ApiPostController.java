package com.md.controllers;

import com.md.pojo.Image;
import com.md.pojo.Post;
import com.md.pojo.Room;
import com.md.pojo.User;
import com.md.service.ImageService;
import com.md.service.PostService;
import com.md.service.RoomService;
import com.md.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiPostController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private ImageService imageService;
    @PostMapping(path ="/post", consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public ResponseEntity<Post> addPostLandLord(@RequestParam("name") String name,
                                                @RequestParam("content") String content,
                                                @RequestParam("username") String username,
                                                @RequestParam("roomId") String roomId,
                                                @RequestParam("files")List<MultipartFile> images) {


        User user = userService.getUserByUsername(username);
        Room room = roomService.getRoomById(roomId);
        Post post = new Post();
        post.setId(String.valueOf(UUID.randomUUID()));
        post.setName(name);
        post.setContent(content);
        post.setUsername(user);
        post.setRoomId(room);
        post.setCreatedDate(new Date());

        if (this.postService.addLandLordPost(post)) {
            if(images.size() > 0) {
                for (MultipartFile image : images) {
                    Image i = new Image();
                    i.setId(String.valueOf(UUID.randomUUID()));
                    i.setUrl(image.getName());
                    i.setPostId(post);
                    i.setFile(image);

                    this.imageService.addImage(i);
                }
            }
            return new ResponseEntity<>(post, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new Post(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getPostsLandLordByUsername() {
        List<Post> posts = this.postService.getPosts();

        return new ResponseEntity<>(posts, HttpStatus.OK);


    }
}
