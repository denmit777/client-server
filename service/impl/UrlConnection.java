package homework14.service.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import homework14.service.WebClient;
import homework14.model.Post;

import java.io.*;

public class UrlConnection implements WebClient {

    private static final String GET_METHOD = "GET";
    private static final String POST_METHOD = "POST";

    @Override
    public Post getPostById(Long id) throws IOException {
        URL url = new URL(GET_ENDPOINT + id);

        final HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod(GET_METHOD);

        try (InputStream stream = connection.getInputStream()) {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            Gson gson = new Gson();

            return gson.fromJson(reader, Post.class);
        }
    }

    @Override
    public Long publishPost(Post post) throws IOException {
        URL url = new URL(POST_ENDPOINT);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod(POST_METHOD);

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        String body = gson.toJson(post);

        connection.setDoOutput(true);

        try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
            wr.writeBytes(body);
        }

        try (InputStream stream = connection.getInputStream()) {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

            return gson.fromJson(reader, Post.class).getId();
        }
    }
}

