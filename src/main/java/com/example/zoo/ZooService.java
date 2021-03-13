package com.example.zoo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ZooService {

    private final ZooRepository repository;

    @Autowired
    public ZooService(ZooRepository bookRepository) {
        this.repository = bookRepository;
    }

    public AnimalEntity createAnimal(AnimalDto animal){
        return repository.save(new AnimalEntity(animal.getName(),animal.getType(),animal.getMood()));
    }

    public List<AnimalDto> getAll() {
        return repository.findAll().stream()
                .map(animalEntity -> {
                    return new AnimalDto(animalEntity.getName(), animalEntity.getType(),animalEntity.getMood());
                })
                .collect(Collectors.toList());
    }
}
