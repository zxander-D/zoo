package com.example.zoo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ZooController {


    List<AnimalDto> zoo;

    ZooController() {
        zoo = new ArrayList<AnimalDto>();
    }

    @PostMapping("/addAnimal")
    @ResponseStatus(value = HttpStatus.CREATED)
    public AnimalDto addAnimal(@RequestBody AnimalDto animalDto) {
        zoo.add(animalDto);
        return animalDto;
    }

    @GetMapping("/getAnimals")
    @ResponseStatus(value = HttpStatus.CREATED)
    public List<AnimalDto> getAnimals() {
        return zoo;
    }

    @PostMapping("/feedAnimal")
    @ResponseStatus(value = HttpStatus.CREATED)
    public AnimalDto getAnimals(@RequestBody AnimalDto animal) {
        if(animal.mood.equals("unhappy")){
            animal.setMood("happy");
        }else if(animal.mood.equals("happy")){
            animal.setMood("happy");
        }
        return animal;
    }

}
