package hu.progmasters.circlesapp.controller;

import hu.progmasters.circlesapp.domain.Post;
import hu.progmasters.circlesapp.dto.incoming.PostCreationCommand;
import hu.progmasters.circlesapp.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final Logger logger = LoggerFactory.getLogger(PostController.class);
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostCreationCommand command){
       Post post = postService.createPost(command);
        logger.info("New post is created");
        return new ResponseEntity<Post>(post, HttpStatus.CREATED);
    }

}
