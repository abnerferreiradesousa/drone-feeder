package com.br.deliveryrobot.service;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.br.deliveryrobot.entity.Video;
import com.br.deliveryrobot.repository.VideoRepository;

@Service
public class VideoService {

  @Autowired
  private VideoRepository videoRepository;

  public void saveVideo(MultipartFile file, String name) throws IOException {
    Video video = new Video(name, file.getBytes());
    videoRepository.save(video);
  }

  public Video downloadVideoById(long videoId) {
    return videoRepository.findById(videoId).orElse(null);
  }

  public List<Video> getAllVideos() {
    return this.videoRepository.findAll();
  }

}
