package com.blogpersonnel.jaime.repository;

import com.blogpersonnel.jaime.dto.LikeDtoResp;
import com.blogpersonnel.jaime.entites.Jaime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Jaime, Integer> {
}
