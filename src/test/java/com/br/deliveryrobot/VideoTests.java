package com.br.deliveryrobot;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class VideoTests extends AbstractContainerBaseTest {

  @Autowired
  private MockMvc mockMvc;

  @Order(1)
  @Test
  void givenVideo_whenUploadVideo_thenReturnMessage() throws Exception {
    // MultipartFile file = mock(MultipartFile.class);
    String fileName = "meuvideo.mp4";
    MockMultipartFile mockMultipartFile =
        new MockMultipartFile("user-file", fileName, "video/mp4", "test data".getBytes());

    ResultActions response = mockMvc.perform(
        MockMvcRequestBuilders.multipart("/api/videos").file("file", mockMultipartFile.getBytes()));

    response.andExpect(status().isOk());
    response.andExpect(jsonPath("$", is("Vídeo salvo com sucesso!")));
  }

  @Order(2)
  @Test
  void givenVideo_whenGetAllVideos_thenReturnAllVideos() throws Exception {
    ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/videos"));

    response.andExpect(status().isOk());
    response.andExpect(jsonPath("$.size()", CoreMatchers.is(Integer.valueOf(1))));
  }

  @Order(3)
  @Test
  void givenVideo_whenGetVideoByDownloading_thenDownloadedIt() throws Exception {
    ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/videos/download/1"));
    response.andExpect(status().isOk());
    response.andReturn();
    response.andExpect(content().contentType(MediaType.APPLICATION_OCTET_STREAM));
  }

  @Order(4)
  @Test
  void givenVideo_whenGetVideo_thenReturnVideo() throws Exception {
    ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/videos/1"));
    response.andExpect(status().isOk());
    response.andReturn();
  }

}
