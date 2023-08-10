package com.br.deliveryrobot.controller;

import java.io.IOException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.br.deliveryrobot.service.VideoService;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

  private VideoService videoService;

  public ResponseEntity<String> saveVideo(@RequestParam("file") MultipartFile file,
      @RequestParam("name") String name) throws IOException {
    videoService.saveVideo(file, name);
    return ResponseEntity.ok("Vídeo salvo com sucesso!");
  }

}
