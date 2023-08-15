package com.br.deliveryrobot.service;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.br.deliveryrobot.entity.Video;
import com.br.deliveryrobot.exceptions.NotFoundException;
import com.br.deliveryrobot.repository.VideoRepository;

@Service
public class VideoService {

  @Autowired
  private VideoRepository videoRepository;

  public void saveVideo(MultipartFile file) throws IOException {
    System.out.println("saveVideoService" + file.getOriginalFilename());
    Video video = new Video(file.getOriginalFilename(), file.getBytes());
    this.videoRepository.save(video);
  }

  public Video downloadVideoById(long videoId) {
    return this.getById(videoId);
  }

  public Video getById(long videoId) {
    return this.videoRepository.findById(videoId)
        .orElseThrow(() -> new NotFoundException("Vídeo não encontrado!"));
  }

  public List<Video> getAllVideos() {
    return this.videoRepository.findAll();
  }

}
