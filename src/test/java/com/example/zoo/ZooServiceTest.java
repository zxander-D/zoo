package com.example.zoo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

public class ZooServiceTest {

    @Autowired
    ZooService service;

    @MockBean
    ZooRepository repository;


    @Test
    public void getAllTest(){
        service.getAll();
    }
}
