package hu.progmasters.circlesapp.service;

import hu.progmasters.circlesapp.domain.Post;
import hu.progmasters.circlesapp.dto.incoming.PostCreationCommand;
import hu.progmasters.circlesapp.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(PostCreationCommand command) {
        return null;
    }
}
