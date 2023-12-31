package com.blogpersonnel.jaime.controller;

import com.blogpersonnel.jaime.dto.LikeDtoCreate;
import com.blogpersonnel.jaime.dto.LikeDtoResp;
import com.blogpersonnel.jaime.entites.Jaime;
import com.blogpersonnel.jaime.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/like")
@CrossOrigin
public class LikeController {

    @Autowired
    LikeService likeService;

    @GetMapping("/ALL")
    public List<LikeDtoResp> findAllComm(){
        return likeService.findAllComm();
    }

    @PostMapping("/save")
    public ResponseEntity<Jaime> save(@RequestBody LikeDtoCreate commentaire) {
        Jaime c = Jaime.builder()
                .blog_id(commentaire.getBlog_id())
                .client_id(commentaire.getClient_id()).dateCreate(Instant.now())
                .build();

        Jaime savedCommentaire = likeService.save(c);
        return new ResponseEntity<>(savedCommentaire, HttpStatus.CREATED);
    }

    @GetMapping("/blog_id/{blog_id}")
    public List<LikeDtoResp> findByBlog(@PathVariable("blog_id") int blog_id){
        return likeService.findByBlogId(blog_id);

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {

        Optional<Jaime> comm = likeService.getOne(id);
        if (comm.isEmpty()) {
            return new ResponseEntity<>("n'existe pas", HttpStatus.NOT_FOUND);
        }
        likeService.delete(id);
        return new ResponseEntity<>("like supprimée !", HttpStatus.OK);

    }
}
