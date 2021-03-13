package com.example.zoo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
}
