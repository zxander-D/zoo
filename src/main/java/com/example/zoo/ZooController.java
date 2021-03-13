package com.example.zoo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ZooController {

    @Autowired
    ZooService service;

    List<AnimalDto> zoo;
    List<Habitat> animalhabitat;

    ZooController() {
        zoo = new ArrayList<AnimalDto>();
        animalhabitat = new ArrayList<>();
        animalhabitat.add(new Habitat("nest",null));
        animalhabitat.add(new Habitat("ocean",null));
        animalhabitat.add(new Habitat("forest",null));
    }

    @PostMapping("/addAnimal")
    @ResponseStatus(value = HttpStatus.CREATED)
    public AnimalEntity addAnimal(@RequestBody AnimalDto animalDto) {
        return service.createAnimal(animalDto);
//        zoo.add(animalDto);
//        return animalDto;
    }

    @GetMapping("/getAnimals")
    @ResponseStatus(value = HttpStatus.CREATED)
    public List<AnimalDto> getAnimals() {
        return service.getAll();
//        return zoo;
    }

    @PostMapping("/feedAnimal")
    @ResponseStatus(value = HttpStatus.CREATED)
    public AnimalDto getAnimals(@RequestBody AnimalDto animal) {
        for (AnimalDto listAnimal : zoo) {
            if (listAnimal.name.equals(animal.name)) {
                if (listAnimal.mood.equals("unhappy")) {
                    listAnimal.setMood("happy");
                } else if (listAnimal.mood.equals("happy")) {
                    listAnimal.setMood("happy");
                }
                return listAnimal;
            }
        }
        return null;
    }

    @PostMapping("/addToHabitat")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addToHabitat(@RequestParam("habitat") String habitatname,@RequestBody AnimalDto animal){

    }
}
