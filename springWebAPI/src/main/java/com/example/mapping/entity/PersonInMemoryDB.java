// File: src/main/java/com/example/demo/service/PersonInMemoryDB.java
package com.example.mapping.entity;

import com.example.mapping.dto.PersonDto;
import java.util.ArrayList;
import java.util.List;

public enum PersonInMemoryDB {
    INSTANCE;

    private static Integer lastId = 0;
    private static List<PersonDto> list = new ArrayList<>();

    public Integer getId() {
        return ++lastId;
    }

    public void add(PersonDto personDto) {
        personDto.setId(getId());
        list.add(personDto);
    }

    public List<PersonDto> findAll() {
        return list;
    }

    public PersonDto findById(Integer id) {
        for (PersonDto personDto : list) {
            if (personDto.getId().equals(id)) return personDto;
        }
        return null;
    }

    public void remove(Integer id) {
        PersonDto target = null;
        for (PersonDto personDto : list) {
            if (personDto.getId().equals(id)) {
                target = personDto;
                break;
            }
        }
        if (target != null) list.remove(target);
    }

    public void edit(PersonDto personDto) {
        for (PersonDto dto : list) {
            if (dto.getId().equals(personDto.getId())) {
                dto.setName(personDto.getName());
                dto.setNumbers(personDto.getNumbers());
            }
        }
    }
}
