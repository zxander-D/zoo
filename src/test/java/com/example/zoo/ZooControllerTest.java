package com.example.zoo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ZooControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    /*Rule: Animal should have a name and a type (flying, swimming, walking)

    When I add an animalDto
    Then it is in my zoo*/
    @Test
    public void addAnimalTest() throws Exception {
        AnimalDto animalDto = new AnimalDto("fish","swimming");

        mockMvc.perform(post("/addAnimal")
        .content(objectMapper.writeValueAsString(animalDto)).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated())
        .andExpect(jsonPath("name").value("fish"));

        animalDto = new AnimalDto("dog","walking");
        mockMvc.perform(post("/addAnimal")
                .content(objectMapper.writeValueAsString(animalDto)).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated())
                .andExpect(jsonPath("name").value("dog"));
    }
    @Test

    /*Given I have added animals to my zoo
    When I check my zoo
    Then I see all the animals*/

    public void getAnimalTest() throws Exception{
        AnimalDto animalDto = new AnimalDto("fish","swimming");

        mockMvc.perform(post("/addAnimal")
                .content(objectMapper.writeValueAsString(animalDto)).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated())
                .andExpect(jsonPath("name").value("fish"));


        mockMvc.perform(get("/getAnimals")
                        ).andExpect(status().isCreated())
                .andExpect(jsonPath("[0].name").value("fish"));

    }

}
