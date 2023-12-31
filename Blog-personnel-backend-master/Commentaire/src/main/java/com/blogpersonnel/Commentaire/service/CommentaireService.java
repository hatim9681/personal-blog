package com.blogpersonnel.Commentaire.service;

import com.blogpersonnel.Commentaire.dto.CommentDtoRes;
import com.blogpersonnel.Commentaire.entites.Blog;
import com.blogpersonnel.Commentaire.entites.Client;
import com.blogpersonnel.Commentaire.entites.Commntaire;
import com.blogpersonnel.Commentaire.exception.CommentNotFoundForBlogIdException;
import com.blogpersonnel.Commentaire.repository.CommentaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.*;

@Service
public class CommentaireService {

    @Autowired
    CommentaireRepository commentaireRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String URL_BLOG = "http://localhost:8081";
    private final String URL_CLIENT = "http://localhost:8084";


    public List<CommentDtoRes> findAllComm() {
    List<Commntaire> commntaires = commentaireRepository.findAll();
        ResponseEntity<Client []> response_client = restTemplate.getForEntity(this.URL_CLIENT+"/api/client", Client[].class);
        Client [] clients = response_client.getBody();
        ResponseEntity<Blog []> response_blog = restTemplate.getForEntity(this.URL_BLOG+"/api/blog", Blog[].class);
        Blog [] blogs = response_blog.getBody();
        return commntaires.stream().map((Commntaire comment)-> mapToCommentRepose(comment ,clients,blogs )).toList();

    }

    private CommentDtoRes mapToCommentRepose(Commntaire commntaire , Client[] clients , Blog [] blogs){
        Client foundClient = Arrays.stream(clients).filter(client -> client.getId()==commntaire.getClient_id()).findFirst().orElse(null);
        Blog foundBlog = Arrays.stream(blogs).filter(blog -> blog.getId() == commntaire.getBlog_id()).findFirst().orElse(null);
        return CommentDtoRes.builder()
                .id(commntaire.getId())
                .description(commntaire.getDescription())
                .dateCreate(commntaire.getDateCreate())
                .blog(foundBlog)
                .client(foundClient)
                .build();

    }
        public Commntaire save(Commntaire commntaire){

       return  commentaireRepository.save(commntaire);
    }
    public void delete(int id){
        commentaireRepository.deleteById(id);
    }
    public Optional<Commntaire> getOne(int id){
        return commentaireRepository.findById(id);
    }
    public List<Commntaire> findAll() {
        return commentaireRepository.findAll();
    }

    public boolean exists(int id){
        return commentaireRepository.existsById(id);
    }

    public List<CommentDtoRes> findByBlogId(int blog_id){
        List<Commntaire> comments = commentaireRepository.findAll().stream().filter(like -> like.getBlog_id() == blog_id).toList();

        if (comments == null) {
            return Collections.emptyList(); // Return an empty list if comments is null
        }

        ResponseEntity<Client []> response_client = restTemplate.getForEntity(this.URL_CLIENT+"/api/client", Client[].class);
        Client [] clients = response_client.getBody();
        ResponseEntity<Blog []> response_blog = restTemplate.getForEntity(this.URL_BLOG+"/api/blog", Blog[].class);
        Blog [] blogs = response_blog.getBody();
        return comments.stream().map((Commntaire comment)-> mapToCommentRepose(comment ,clients,blogs )).toList();
    }
    private CommentDtoRes mapToCommentReposes(Commntaire commntaire , Client[] clients , Blog [] blogs){
        Client foundClient = Arrays.stream(clients).filter(client -> client.getId()==commntaire.getClient_id()).findFirst().orElse(null);
        Blog foundBlog = Arrays.stream(blogs).filter(blog -> blog.getId() == commntaire.getBlog_id()).findFirst().orElse(null);
        return CommentDtoRes.builder()
                .id(commntaire.getId())
                .description(commntaire.getDescription())
                .dateCreate(commntaire.getDateCreate())
                .blog(foundBlog)
                .client(foundClient)
                .build();

    }
}
