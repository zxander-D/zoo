package com.example.zoo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ZooServiceTest {

    @InjectMocks
    ZooService service;

    @Mock
    ZooRepository repository;

    @Test
    public void createAnimalTest(){
        AnimalDto animalEntity = new AnimalDto("kangaroo","walking","unhappy");
        service.createAnimal(animalEntity);
        verify(repository).save(
                new AnimalEntity("kangaroo","walking","unhappy")
        );
    }

    @Test
    public void findAll(){
        // S Seat
        AnimalEntity AnimalEntity = new AnimalEntity("kangaroo","walking","unhappy");
        when(repository.findAll()).thenReturn(
                List.of(
                        AnimalEntity,
                        new AnimalEntity("elephant","walking","unhappy")
                )
        );

        // E Exercise
        List<AnimalDto> actual = service.getAll();

        // A Assert
        assertThat(actual).isEqualTo(
                List.of(
                        new AnimalDto("kangaroo","walking","unhappy"),
                        new AnimalDto("elephant","walking","unhappy")
                )
        );
    }
}
