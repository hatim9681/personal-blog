package com.blogpersonnel.Client.repository;

import com.blogpersonnel.Client.dto.ClientResponse;
import com.blogpersonnel.Client.entites.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client , Integer> {

    Optional<Client> findByEmail(String email);

}
