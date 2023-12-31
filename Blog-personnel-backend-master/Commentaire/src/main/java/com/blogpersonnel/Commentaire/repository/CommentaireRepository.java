package com.blogpersonnel.Commentaire.repository;

import com.blogpersonnel.Commentaire.entites.Commntaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentaireRepository  extends JpaRepository<Commntaire , Integer> {
}
