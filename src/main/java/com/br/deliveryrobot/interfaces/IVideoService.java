package com.br.deliveryrobot.interfaces;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.br.deliveryrobot.entity.Video;


public interface IVideoService {

  /**
   * Save a given video on database.
   * 
   * @param file Content into array of bytes format.
   */
  void saveVideo(MultipartFile file);

  /**
   * Download video by id.
   * 
   * @param videoId Video id that will be searched.
   * @return If video exists will return the video in bytes format for download, otherwise it will
   *         throw an exception.
   */
  Video downloadVideoById(long videoId);

  /**
   * Find video using a given drone's id.
   * 
   * @param videoId Video id that will be searched.
   * @return If video exists will return the own video, otherwise it will throw an exception.
   */
  Video getById(long videoId);

  /**
   * Get all videos on database.
   * 
   * @return All videos on database.
   */
  List<Video> getAllVideos();

}
