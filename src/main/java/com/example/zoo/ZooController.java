package com.example.zoo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZooController {

    @PostMapping("/addAnimal")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addAnimal() {

    }
}
