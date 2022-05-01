package homework14.service;

import homework14.service.impl.HttpClient;
import homework14.service.impl.UrlConnection;
import homework14.exception.IncorrectInputException;
import homework14.model.Post;

import java.io.IOException;

public class Manager {

    WebClient urlConnectionClient = new UrlConnection();
    WebClient httpClient = new HttpClient();

    private static final String URL_CONNECTION_TASK = "task1";
    private static final String HTTP_CLIENT_TASK = "task2";
    private static final String GET_METHOD = "GET";
    private static final String POST_METHOD = "POST";

    public void findWebClientTask(String task, String method, Long id) {
        try {
            Post post1 = urlConnectionClient.getPostById(id);
            Long newPostId1 = urlConnectionClient.publishPost(post1);
            Post post2 = httpClient.getPostById(id);
            Long newPostId2 = httpClient.publishPost(post2);

            switch (task) {
                case URL_CONNECTION_TASK:
                    switch (method) {
                        case GET_METHOD:
                            System.out.println(post1);
                            break;
                        case POST_METHOD:
                            System.out.println(newPostId1);
                            break;
                        default:
                            throw new IncorrectInputException("Incorrect method");
                    }
                    break;
                case HTTP_CLIENT_TASK:
                    switch (method) {
                        case GET_METHOD:
                            System.out.println(post2);
                            break;
                        case POST_METHOD:
                            System.out.println(newPostId2);
                            break;
                        default:
                            throw new IncorrectInputException("Incorrect method");
                    }
            }
        } catch (IOException | IncorrectInputException e) {
            e.printStackTrace();
        }
    }
}
