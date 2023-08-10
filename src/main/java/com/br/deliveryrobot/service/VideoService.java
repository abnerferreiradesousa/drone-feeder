package com.br.deliveryrobot.service;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import com.br.deliveryrobot.entity.Video;
import com.br.deliveryrobot.repository.VideoRepository;

public class VideoService {

  private VideoRepository videoRepository;

  public void saveVideo(MultipartFile file, String name) throws IOException {
    Video video = new Video(name, file.getBytes());
    videoRepository.save(video);
  }

}
