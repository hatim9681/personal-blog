package com.blogpersonnel.Client.controller;

import com.blogpersonnel.Client.dto.AuthenticationClient;
import com.blogpersonnel.Client.dto.ClientResponse;
import com.blogpersonnel.Client.dto.RegisterClient;
import com.blogpersonnel.Client.entites.Client;
import com.blogpersonnel.Client.exception.ClientHandleException;
import com.blogpersonnel.Client.exception.DifferentPasswordException;
import com.blogpersonnel.Client.repository.ClientRepository;
import com.blogpersonnel.Client.service.ClientService;
import com.blogpersonnel.Client.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequestMapping("/api/client")
@RestController
@CrossOrigin

public class clientController {
    @Autowired
    CloudinaryService cloudinaryService;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientService clientService;

    @PostMapping("/register")
    public Client register(@RequestBody RegisterClient client) {
        return clientService.save(client);
    }


    @RequestMapping
    public List<Client> getAllClient() {
        return clientService.findAllClient();
    }

    @PostMapping("/auth")
    public ClientResponse authen(@RequestBody AuthenticationClient authenticationClient) {


        return clientService.authentication(authenticationClient);
    }

    @GetMapping("/{id}")
    public Optional<Client> findById(@PathVariable("id") int id){
        return clientService.getOne(id);
    }


    @PostMapping("/register1")
    @ResponseBody
    public ResponseEntity<ClientResponse> registerClient(
            @RequestParam("file") MultipartFile multipartFile,
            @RequestParam("nom") String nom,
            @RequestParam("telephone") String telephone,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("confirm_password") String confirm_password
    ) {
        try {
            // Vérifier la validité de l'image
            BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
            if (bi == null) {
                return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
            }

            // Upload de l'image vers Cloudinary
            Map<String, String> result = cloudinaryService.upload(multipartFile);
            Optional<Client> c = clientRepository.findByEmail(email);
            if (c.isPresent())
                throw new ClientHandleException("Email already exists", HttpStatus.BAD_REQUEST.toString());
            if (!password.equals(confirm_password))
                throw new DifferentPasswordException("Check the password", HttpStatus.BAD_REQUEST.toString());


            Client client1 = Client.builder()
                    .nom(nom)
                    .telephone(telephone)
                    .email(email)
                    .image(result.get("url"))
                    .password(password)
                    .confirm_password(confirm_password)
                    .build();

            // Sauvegarde de l'objet Blog dans la base de données
            clientRepository.save(client1);
            ClientResponse rs = ClientResponse.builder()
                    .id(client1.getId())
                    .nom(client1.getNom())
                    .image(client1.getImage())
                    .telephone(client1.getTelephone())
                    .email(client1.getEmail())
                    .build();

            // Réponse de succès
            return new ResponseEntity<>(rs, HttpStatus.OK);
        } catch (IOException e) {
            // Gestion des exceptions liées à la lecture de l'image
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
