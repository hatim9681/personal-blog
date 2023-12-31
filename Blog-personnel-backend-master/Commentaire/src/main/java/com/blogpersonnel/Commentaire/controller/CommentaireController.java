package com.blogpersonnel.Commentaire.controller;

import com.blogpersonnel.Commentaire.dto.CommentDtoRes;
import com.blogpersonnel.Commentaire.dto.CommentaireDto;
import com.blogpersonnel.Commentaire.entites.Commntaire;
import com.blogpersonnel.Commentaire.service.CommentaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/commentaire")
@CrossOrigin

public class CommentaireController {

    @Autowired
    CommentaireService commentaireService;

    @GetMapping
    public List<CommentDtoRes> findAllComm(){
        return commentaireService.findAllComm();
    }

    @PostMapping("/save")
    public ResponseEntity<Commntaire> save(@RequestBody CommentaireDto commentaire) {
        Commntaire c = Commntaire.builder()
                .description(commentaire.getDescription())
                .blog_id(commentaire.getBlog_id())
                .client_id(commentaire.getClient_id())
                .dateCreate(Instant.now())
                .build();


        Commntaire savedCommentaire = commentaireService.save(c);


        return new ResponseEntity<>(savedCommentaire, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {

        Optional<Commntaire> comm = commentaireService.getOne(id);
        if (comm.isEmpty()) {
            return new ResponseEntity<>("n'existe pas", HttpStatus.NOT_FOUND);
        }
        commentaireService.delete(id);
        return new ResponseEntity<>("commentaire supprimée !", HttpStatus.OK);

    }

    @GetMapping("/blog_id/{blog_id}")
    public List<CommentDtoRes> findByBlog(@PathVariable("blog_id") int blog_id){
        return commentaireService.findByBlogId(blog_id);

    }

}
