package com.blogpersonnel.Client.service;

import com.blogpersonnel.Client.dto.AuthenticationClient;
import com.blogpersonnel.Client.dto.ClientResponse;
import com.blogpersonnel.Client.dto.RegisterClient;
import com.blogpersonnel.Client.entites.Client;
import com.blogpersonnel.Client.exception.ClientHandleException;
import com.blogpersonnel.Client.exception.DifferentPasswordException;
import com.blogpersonnel.Client.exception.InvalidPasswordException;
import com.blogpersonnel.Client.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;


    public Client save( RegisterClient client) {

        Optional<Client> c = clientRepository.findByEmail(client.getEmail());
        if (c.isPresent())
            throw new ClientHandleException("Email already exist", HttpStatus.BAD_REQUEST.toString());
        if (!client.getPassword().equals(client.getConfirm_password()))
            throw new DifferentPasswordException("Check the password", HttpStatus.BAD_REQUEST.toString());
        Client client1 = Client.builder()
                .nom(client.getNom())
                .telephone(client.getTelephone())
                .email(client.getEmail())
                .password(client.getPassword())
                .confirm_password(client.getConfirm_password())
                .build();
        return clientRepository.save(client1);

    }

    public List<Client> findAllClient() {
        return clientRepository.findAll();
    }

    public Optional<Client> getOne(int id){
        return clientRepository.findById(id);
    }

    public ClientResponse authentication(AuthenticationClient authenticationClient){

        Optional<Client> client = clientRepository.findByEmail(authenticationClient.getEmail());
        if(!client.isPresent()){
            throw  new ClientHandleException("Client not found",HttpStatus.NOT_FOUND.toString());
        }

        if( !authenticationClient.getPassword().equals(client.get().getPassword()))
            throw  new InvalidPasswordException("Password invalid", HttpStatus.BAD_REQUEST.toString());
            return  ClientResponse
                    .builder()
                    .id(client.get().getId())
                    .email(client.get().getEmail())
                    .telephone(client.get().getTelephone())
                    .nom(client.get().getNom())
                    .image(client.get().getImage())
                    .build();
    }


}
