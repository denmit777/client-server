package homework14.service;

import homework14.exception.IncorrectInputException;
import homework14.model.Post;

import java.io.IOException;

public interface WebClient {
    String GET_ENDPOINT = "https://jsonplaceholder.typicode.com/posts/";
    String POST_ENDPOINT = "https://jsonplaceholder.typicode.com/posts/";

    Post getPostById(Long id) throws IOException, IncorrectInputException;

    Long publishPost(Post newPost) throws IOException;
}
