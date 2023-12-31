package com.blogpersonnel.jaime.service;


import com.blogpersonnel.jaime.dto.LikeDtoCreate;
import com.blogpersonnel.jaime.dto.LikeDtoResp;
import com.blogpersonnel.jaime.entites.Blog;
import com.blogpersonnel.jaime.entites.Client;
import com.blogpersonnel.jaime.entites.Jaime;
import com.blogpersonnel.jaime.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class LikeService {
    @Autowired
    LikeRepository likeRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String URL_BLOG = "http://localhost:8081";
    private final String URL_CLIENT = "http://localhost:8084";


    public List<LikeDtoResp> findAllComm() {
        List<Jaime> like = likeRepository.findAll();
        ResponseEntity<Client[]> response_client = restTemplate.getForEntity(this.URL_CLIENT+"/api/client", Client[].class);
        Client[] clients = response_client.getBody();
        ResponseEntity<Blog[]> response_blog = restTemplate.getForEntity(this.URL_BLOG+"/api/blog", Blog[].class);
        Blog [] blogs = response_blog.getBody();
        return like.stream().map((Jaime likes)-> mapToCommentRepose(likes ,clients,blogs )).toList();

    }

    private LikeDtoResp mapToCommentRepose(Jaime commntaire , Client[] clients , Blog [] blogs){
        Client foundClient = Arrays.stream(clients).filter(client -> client.getId()==commntaire.getClient_id()).findFirst().orElse(null);
        Blog foundBlog = Arrays.stream(blogs).filter(blog -> blog.getId() == commntaire.getBlog_id()).findFirst().orElse(null);
        return LikeDtoResp.builder()
                .id(commntaire.getId())
                .blog(foundBlog)
                .client(foundClient)
                .build();

    }
    public Jaime save(Jaime likedto){

        return  likeRepository.save(likedto);
    }
    public void delete(int id){
        likeRepository.deleteById(id);
    }
    public Optional<Jaime> getOne(int id){
        return likeRepository.findById(id);
    }
    public List<Jaime> findAll() {
        return likeRepository.findAll();
    }

    public boolean exists(int id){
        return likeRepository.existsById(id);
    }


    public List<LikeDtoResp> findByBlogId(int blog_id){
        List<Jaime> likess = likeRepository.findAll().stream().filter(like -> like.getBlog_id() == blog_id).toList();

        if (likess == null) {
            return Collections.emptyList(); // Return an empty list if comments is null
        }

        ResponseEntity<Client []> response_client = restTemplate.getForEntity(this.URL_CLIENT+"/api/client", Client[].class);
        Client [] clients = response_client.getBody();
        ResponseEntity<Blog []> response_blog = restTemplate.getForEntity(this.URL_BLOG+"/api/blog", Blog[].class);
        Blog [] blogs = response_blog.getBody();
        return likess.stream().map((Jaime comment)-> mapToCommentRepose(comment ,clients,blogs )).toList();
    }
    private LikeDtoResp mapToCommentReposes(Jaime jaime , Client[] clients , Blog [] blogs){
        Client foundClient = Arrays.stream(clients).filter(client -> client.getId()==jaime.getClient_id()).findFirst().orElse(null);
        Blog foundBlog = Arrays.stream(blogs).filter(blog -> blog.getId() == jaime.getBlog_id()).findFirst().orElse(null);
        return LikeDtoResp.builder()
                .id(jaime.getId())
                .dateCreate(jaime.getDateCreate())
                .blog(foundBlog)
                .client(foundClient)
                .build();

    }
}
