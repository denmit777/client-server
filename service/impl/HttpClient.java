package homework14.service.impl;

import com.google.gson.GsonBuilder;
import homework14.service.WebClient;
import homework14.model.Post;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;

public class HttpClient implements WebClient {

    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    private final String USER_AGENT = "Mozilla/5.0";

    @Override
    public Post getPostById(Long id) throws IOException {
        Post post = null;
        HttpGet request = new HttpGet(GET_ENDPOINT + id);

        request.addHeader(HttpHeaders.USER_AGENT, USER_AGENT);

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                String result = EntityUtils.toString(entity);
                GsonBuilder builder = new GsonBuilder();
                Gson g = builder.create();
                builder.setPrettyPrinting();
                post = g.fromJson(result, Post.class);
            }
        }
        return post;
    }

    @Override
    public Long publishPost(Post post) throws IOException {
        HttpPost httpPost = new HttpPost(POST_ENDPOINT);
        GsonBuilder builder = new GsonBuilder();

        builder.setPrettyPrinting();
        Gson gson = builder.create();
        String body = gson.toJson(post);

        StringEntity stringEntity = new StringEntity(body);

        httpPost.getRequestLine();
        httpPost.setEntity(stringEntity);

        Post newPost = new Post();

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httpPost)) {
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                String result = EntityUtils.toString(entity);
                newPost = gson.fromJson(result, Post.class);
            }
        }
        return newPost.getId();
    }
}
