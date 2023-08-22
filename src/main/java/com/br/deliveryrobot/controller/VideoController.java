package com.br.deliveryrobot.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.br.deliveryrobot.entity.Video;
import com.br.deliveryrobot.interfaces.IVideoService;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

  @Autowired
  private IVideoService videoService;

  @PostMapping
  @Transactional
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<String> saveVideo(@RequestParam("file") MultipartFile file) {
    this.videoService.saveVideo(file);
    return ResponseEntity.ok("Vídeo salvo com sucesso!");
  }

  @GetMapping("/download/{videoId}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Resource> downloadVideoById(@PathVariable("videoId") long videoId) {
    Video video = this.videoService.downloadVideoById(videoId);

    Resource videoResource = new ByteArrayResource(video.getData());
    String headerValue = "attachment; filename=\"" + video.getName() + "\"";

    return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
        .header(HttpHeaders.CONTENT_DISPOSITION, headerValue).body(videoResource);
  }

  @GetMapping("{videoId}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Video> getById(@PathVariable(required = true) long videoId) {
    return ResponseEntity.ok(this.videoService.getById(videoId));
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Video> getAllVideos() {
    return this.videoService.getAllVideos();
  }

}
