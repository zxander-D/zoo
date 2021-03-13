package com.example.zoo;

import com.fasterxml.jackson.core.JsonProcessingException;
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
        AnimalDto animalDto = new AnimalDto("fish","swimming", "unhappy");

        mockMvc.perform(post("/addAnimal")
        .content(objectMapper.writeValueAsString(animalDto)).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated())
        .andExpect(jsonPath("name").value("fish"));

        animalDto = new AnimalDto("dog","walking", "unhappy");
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
        AnimalDto animalDto = new AnimalDto("fish","swimming","unhappy");

        mockMvc.perform(post("/addAnimal")
                .content(objectMapper.writeValueAsString(animalDto)).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated())
                .andExpect(jsonPath("name").value("fish"));


        mockMvc.perform(get("/getAnimals")
                        ).andExpect(status().isCreated())
                .andExpect(jsonPath("[0].name").value("fish"));

    }

    /*Rule: Animal moods are unhappy or happy. They are unhappy by default.

    Given an animal is unhappy
    When I give it a treat
    Then the animal is happy

    Given an animal is happy
    When I give it a treat
    Then the animal is still happy*/
    @Test
    public void feedAnimalTest() throws Exception {
        AnimalDto animalDto = new AnimalDto("fish","swimming","unhappy");

        mockMvc.perform(post("/addAnimal")
                .content(objectMapper.writeValueAsString(animalDto)).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());

        //feed animal
        mockMvc.perform(post("/feedAnimal")
                .content(objectMapper.writeValueAsString(animalDto)).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated()).andExpect(jsonPath("mood").value("happy"));

        mockMvc.perform(get("/getAnimals")
        ).andExpect(status().isCreated())
                .andExpect(jsonPath("[0].name").value("fish"))
                .andExpect(jsonPath("[0].mood").value("happy"));
    }

    @Test
    /*Given I have an empty <habitat>
    When I put animal of <type> into a compatible habitat
    Then the animal is in the habitat*/

    public void placeAnimalHabitatTest() throws Exception{
        AnimalDto animalDto = new AnimalDto("fish","swimming","unhappy");

        mockMvc.perform(post("/addAnimal")
                .content(objectMapper.writeValueAsString(animalDto)).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());

        mockMvc.perform(post("/addToHabitat?habitat=ocean")
                .content(objectMapper.writeValueAsString(animalDto)).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
    }

}
