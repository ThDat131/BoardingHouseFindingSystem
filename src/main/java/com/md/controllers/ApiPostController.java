package com.md.controllers;

import com.md.advice.ValidationException;
import com.md.pojo.*;
import com.md.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.security.Principal;
import java.util.*;

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
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private FollowService followService;
    @Autowired
    private LandLordService landLordService;
    @Autowired
    private JavaMailSender javaMailSender;
    @PostMapping(path ="/post/", consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public ResponseEntity<Post> addPostLandLord(@RequestParam("name") String name,
                                                @RequestParam("content") String content,
                                                @RequestParam("roomId") String roomId,
                                                @RequestParam("files")List<MultipartFile> images,
                                                Principal u) {


        User user = userService.getUserByUsername(u.getName());
        Room room = roomService.getRoomById(roomId);
        Post post = new Post();
        post.setId(String.valueOf(UUID.randomUUID()));
        post.setName(name);
        post.setContent(content);
        post.setUsername(user);
        post.setRoomId(room);
        post.setCreatedDate(new Date());
        try {
            if (this.postService.addLandLordPost(post)) {
                if (images.size() > 0) {
                    for (MultipartFile image : images) {
                        Image i = new Image();
                        i.setId(String.valueOf(UUID.randomUUID()));
                        i.setUrl(image.getName());
                        i.setPostId(post);
                        i.setFile(image);

                        this.imageService.addImage(i);
                    }
                }
                LandLord l = this.landLordService.getLandLordByUsername(user.getUsername());
                List<Tentant> list = this.followService.getFollowerByLandLordId(l.getId());
                if (list.size() > 0) {
                    list.forEach(tentant -> {
                        MimeMessage message = javaMailSender.createMimeMessage();
                        try {
                            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
                            helper.setTo(tentant.getEmail());
                            helper.setSubject("Thông báo từ " + l.getFullName() + ": " + post.getName());
                            helper.setText(post.getContent(), true);
                            javaMailSender.send(message);
                        } catch (MessagingException e) {
                            throw new RuntimeException(e);
                        }

                    });
                }

                return new ResponseEntity<>(post, HttpStatus.CREATED);
            }

        } catch (ValidationException ve) {
            Map<String, List<String>> errors = ve.getErrors();
            Map<String, List<String>> errorsRes = new HashMap<>();
            errors.forEach((key, value) -> {
                List<String> listErr = new ArrayList<>();
                for (String errCode : value)
                    listErr.add(messageSource.getMessage(errCode, null, Locale.getDefault()));
                errorsRes.put(key, listErr);
            });
            return new ResponseEntity(errorsRes, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new Post(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/posts/")
    public ResponseEntity<List<Post>> getPostsLandLord(@RequestParam Map<String, String> params) {
        List<Post> posts = this.postService.getPosts(params);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/post/{id}/")
    public ResponseEntity<Post> getPostById(@PathVariable(value = "id") String id) {
        Post post = this.postService.getPostById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
    @GetMapping("/posts-tentant/")
    public ResponseEntity<List<Post>> getPostsTentant() {
        List<Post> posts = this.postService.getPostOfTentant();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
    @PostMapping("/post-tentant/")
    public ResponseEntity<Post> addPostTentant(@RequestBody Map<String, String> params, Principal user) {
        try {
            Post p = postService.addTentantPost(params, user);
            return new ResponseEntity<>(p, HttpStatus.CREATED);
        } catch (ValidationException ve) {
            Map<String, List<String>> errors = ve.getErrors();
            Map<String, List<String>> errorsRes = new HashMap<>();
            errors.forEach((key, value) -> {
                List<String> listErr = new ArrayList<>();
                for (String errCode : value)
                    listErr.add(messageSource.getMessage(errCode, null, Locale.getDefault()));
                errorsRes.put(key, listErr);
            });
            return new ResponseEntity(errorsRes, HttpStatus.BAD_REQUEST);
        }
    }
}
