package com.br.deliveryrobot.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.br.deliveryrobot.entity.Video;
import com.br.deliveryrobot.exceptions.NotFoundException;
import com.br.deliveryrobot.repository.VideoRepository;

/**
 * Layer that makes data's validate and communicate with the DAO layer to persist using his methods.
 */
@Service
public class VideoService {

  @Autowired
  private VideoRepository videoRepository;

  /**
   * Save a given video on database.
   * 
   * @param file Content into array of bytes format.
   */
  public void saveVideo(MultipartFile file) {
    try {
      System.out.println("saveVideoService" + file.getOriginalFilename());
      Video video = new Video(file.getOriginalFilename(), file.getBytes());
      this.videoRepository.save(video);
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
  }

  /**
   * Download video by id.
   * 
   * @param videoId Video id that will be searched.
   * @return If video exists will return the video in bytes format for download, otherwise it will
   *         throw an exception.
   */
  public Video downloadVideoById(long videoId) {
    return this.getById(videoId);
  }


  /**
   * Find video using a given drone's id.
   * 
   * @param videoId Video id that will be searched.
   * @return If video exists will return the own video, otherwise it will throw an exception.
   */
  public Video getById(long videoId) {
    return this.videoRepository.findById(videoId)
        .orElseThrow(() -> new NotFoundException("Vídeo não encontrado!"));
  }

  /**
   * Get all videos on database.
   * 
   * @return All videos on database.
   */
  public List<Video> getAllVideos() {
    return this.videoRepository.findAll();
  }

}
