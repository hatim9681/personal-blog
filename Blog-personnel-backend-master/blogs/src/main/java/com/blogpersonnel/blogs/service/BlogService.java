package com.blogpersonnel.blogs.service;
import com.blogpersonnel.blogs.dto.BlogDto;
import com.blogpersonnel.blogs.entites.Blog;
import com.blogpersonnel.blogs.entites.Client;
import com.blogpersonnel.blogs.entites.Commntaire;
import com.blogpersonnel.blogs.entites.Jaime;
import com.blogpersonnel.blogs.repository.BlogRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private RestTemplate restTemplate;
    private final String URL_CLIENT= "http://localhost:8084";
    private final String URL_COMMENT= "http://localhost:8088";
    private final String URL_LIKE= "http://localhost:8086";


    public  List<BlogDto> findAllBlogs(){
        List<Blog> blogs = blogRepository.findAll();
        ResponseEntity<Client []> response = restTemplate.getForEntity(this.URL_CLIENT+"/api/client", Client[].class);
        Client [] clients = response.getBody();

        return blogs.stream().map((Blog blogg)->mapToBlogReponse(blogg , clients )).toList();

    }

    private BlogDto mapToBlogReponse (Blog blog , Client[] clients){
        Client foudClient = Arrays.stream(clients).filter(client -> client.getId() ==blog.getCreateur_id()).findFirst().orElse(null);


        return BlogDto.builder()


                .imageUrl(blog.getImageUrl())
                .imageId(blog.getImageId())
                .dateCreate(Instant.now())
                .client(foudClient)

                .build();
    }


    public void save(Blog image){
        blogRepository.save(image);
    }
    public void delete(int id){
        blogRepository.deleteById(id);
    }
    public Optional<Blog> getOne(int id){
        return blogRepository.findById(id);
    }
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    public boolean exists(int id){
        return blogRepository.existsById(id);
    }

    public List<BlogDto> findAllBlogs1() {
        List<Blog> blogs = blogRepository.findAll();
        ResponseEntity<Client[]> response = restTemplate.getForEntity(URL_CLIENT + "/api/client", Client[].class);
        Client[] clients = response.getBody();

        return blogs.stream()
                .sorted(Comparator.comparing(Blog::getDateCreate).reversed()) // Tri par date de création descendante
                .map(blog -> mapToBlogResponse1(blog, clients))
                .collect(Collectors.toList());
    }

    private BlogDto mapToBlogResponse1(Blog blog, Client[] clients) {
        Client foundClient = Arrays.stream(clients)
                .filter(client -> client.getId() == blog.getCreateur_id())
                .findFirst()
                .orElse(null);

        ResponseEntity<Commntaire[]> responseCom = restTemplate.getForEntity(URL_COMMENT + "/api/commentaire/blog_id/" + blog.getId(), Commntaire[].class);
        Commntaire[] commntaires = responseCom.getBody();
        ResponseEntity<Jaime[]> responseLike = restTemplate.getForEntity(URL_LIKE + "/api/like/blog_id/" + blog.getId(), Jaime[].class);
        Jaime[] likes = responseLike.getBody();

        return BlogDto.builder()
                .id(blog.getId())
                .titre(blog.getTitre())
                .imageUrl(blog.getImageUrl())
                .imageId(blog.getImageId())
                .description(blog.getDescription())
                .dateCreate(Instant.now())
                .client(foundClient)
                .commntaires(commntaires)
                .jaimes(likes)
                .build();
    }



    }
