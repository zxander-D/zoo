package com.example.zoo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
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
        List<AnimalEntity> returnList = new ArrayList<>();
        // S Seat
        AnimalEntity animalEntity = new AnimalEntity("kangaroo","walking","unhappy");
        returnList.add(animalEntity);
        returnList.add(new AnimalEntity("elephant","walking","unhappy"));
        when(repository.findAll()).thenReturn(returnList);

        // E Exercise
        List<AnimalDto> actual = service.getAll();

        List<AnimalDto> expectedList = new ArrayList<>();
        expectedList.add(new AnimalDto("kangaroo","walking","unhappy"));
        expectedList.add(new AnimalDto("elephant","walking","unhappy"));
        // A Assert
        assertThat(actual).isEqualTo(expectedList);
    }
}
