package com.br.deliveryrobot.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.br.deliveryrobot.entity.Video;
import com.br.deliveryrobot.exceptions.NotFoundException;
import com.br.deliveryrobot.interfaces.IVideoService;
import com.br.deliveryrobot.repository.VideoRepository;

/**
 * Layer that makes data's validate and communicate with the DAO layer to persist using his methods.
 */
@Service
public class VideoService implements IVideoService {

  @Autowired
  private VideoRepository videoRepository;

  /**
   * Save a given video on database.
   * 
   * @param file Content into array of bytes format.
   */
  @Override
  public Video saveVideo(MultipartFile file) {
    Video videoCreated = null;
    try {
      System.out.println("saveVideoService" + file.getOriginalFilename());
      Video video = new Video(file.getOriginalFilename(), file.getBytes());
      videoCreated = this.videoRepository.save(video);
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
    return videoCreated;
  }

  /**
   * Download video by id.
   * 
   * @param videoId Video id that will be searched.
   * @return If video exists will return the video in bytes format for download, otherwise it will
   *         throw an exception.
   */
  @Override
  public Video downloadVideoById(long videoId) {
    return this.getById(videoId);
  }

  /**
   * Find video using a given drone's id.
   * 
   * @param videoId Video id that will be searched.
   * @return If video exists will return the own video, otherwise it will throw an exception.
   */
  @Override
  public Video getById(long videoId) {
    Video videoSearched = this.videoRepository.findById(videoId)
        .orElseThrow(() -> new NotFoundException("Vídeo não encontrado!"));

    return videoSearched;
  }

  /**
   * Get all videos on database.
   * 
   * @return All videos on database.
   */
  @Override
  public List<Video> getAllVideos() {
    return this.videoRepository.findAll();
  }

}
