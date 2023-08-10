package com.br.deliveryrobot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.br.deliveryrobot.entity.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
  @Query(value = "SELECT * FROM video WHERE name = ?", nativeQuery = true)
  Video findByName(String name);
}
