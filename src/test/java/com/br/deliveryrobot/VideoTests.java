package com.br.deliveryrobot;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;
import com.br.deliveryrobot.repository.VideoRepository;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class VideoTests extends AbstractContainerBaseTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private VideoRepository videoRepository;

  @Order(1)
  @Test
  void givenVideo_whenUploadVideo_thenReturnMessage() throws Exception {
    MultipartFile file = mock(MultipartFile.class);
    ResultActions response = mockMvc.perform(MockMvcRequestBuilders.multipart("/api/videos")
        .file("file", file.getBytes()).param("name", "meuVideo"));

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
  void givenVideo_whenGetVideo_thenDownloadIt() throws Exception {
    ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/videos/1"));
    response.andExpect(status().isOk());
    response.andReturn();
    response.andExpect(content().contentType(MediaType.APPLICATION_OCTET_STREAM));
  }

}
