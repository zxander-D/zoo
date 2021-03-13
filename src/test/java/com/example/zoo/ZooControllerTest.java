package com.example.zoo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ZooControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void addAnimalTest() throws Exception {
        Animal animalDto = new Animal("fish","swimming");

        mockMvc.perform(post("/addAnimal")
        .content(objectMapper.writeValueAsString(animalDto)).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated())
        .andExpect(jsonPath("name").value("fish"));

    }
}
