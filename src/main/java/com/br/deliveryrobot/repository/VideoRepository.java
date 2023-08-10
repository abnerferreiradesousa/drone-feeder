package com.br.deliveryrobot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.br.deliveryrobot.entity.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
