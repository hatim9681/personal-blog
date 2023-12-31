package com.blogpersonnel.blogs.controller;

import com.blogpersonnel.blogs.dto.BlogDto;
import com.blogpersonnel.blogs.entites.Blog;
import com.blogpersonnel.blogs.service.BlogService;
import com.blogpersonnel.blogs.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/blog")
public class BlogController {
    @Autowired
    CloudinaryService cloudinaryService;

    @Autowired
    BlogService blogService;
    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<BlogDto> upload(
            @RequestParam("file") MultipartFile multipartFile,
            @RequestParam("titre") String titre,
            @RequestParam("description") String description,
            @RequestParam("createur_id") int createur_id
    ) {
        try {
            // Vérifier la validité de l'image
            BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
            if (bi == null) {
                return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
            }

            // Upload de l'image vers Cloudinary
            Map<String, String> result = cloudinaryService.upload(multipartFile);


            Blog image = Blog.builder()
                    .description(description)
                    .titre(titre)
                    .imageUrl(result.get("url"))
                    .imageId(result.get("public_id"))
                    .dateCreate(Instant.now())
                    .createur_id(createur_id)
                    .build();

            // Sauvegarde de l'objet Blog dans la base de données
            blogService.save(image);
            BlogDto blogDto = BlogDto.builder()
                    .id(image.getId())
                    .dateCreate(image.getDateCreate())
                    .description(image.getDescription())

                    .imageId(image.getImageUrl())
                    .titre(image.getTitre())
                    .build();

            // Réponse de succès
            return new ResponseEntity<>(blogDto, HttpStatus.OK);
        } catch (IOException e) {
            // Gestion des exceptions liées à la lecture de l'image
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        Optional<Blog> imageOptional = blogService.getOne(id);
        if (imageOptional.isEmpty()) {
            return new ResponseEntity<>("n'existe pas", HttpStatus.NOT_FOUND);
        }
        Blog image = imageOptional.get();
        String cloudinaryImageId = image.getImageId();
        try {
            cloudinaryService.delete(cloudinaryImageId);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to delete blog from Cloudinary", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        blogService.delete(id);
        return new ResponseEntity<>("blog supprimée !", HttpStatus.OK);
    }

    @GetMapping
    @ResponseBody
    public List<BlogDto> getAll() {
        return blogService.findAllBlogs();
    }

    @GetMapping("/all")
    @ResponseBody
    public List<BlogDto> getAllComm() {
        return blogService.findAllBlogs1();
    }



}
