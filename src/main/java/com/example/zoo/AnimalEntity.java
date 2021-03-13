package com.example.zoo;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AnimalEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    long id;
    String name;
    String type;
    String mood;

    public AnimalEntity(String name, String type, String mood) {
        this.name = name;
        this.type = type;
        this.mood = mood;
    }
}
