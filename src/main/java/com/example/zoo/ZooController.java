package com.example.zoo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ZooController {

    List<Animal> zoo;

    @PostMapping("/addAnimal")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String addAnimal() {
        return "{\"name\": \"fish\", \"type\": \"swimming\"}";
    }
}
