// File: src/main/java/com/example/demo/controller/PersonMockController.java
package com.example.mapping.controller;

import com.example.mapping.dto.PersonDto;
import com.example.mapping.entity.PersonInMemoryDB;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mock")
public class PersonMockController {

    @PostMapping("/create")
    public void create(@RequestBody PersonDto personDto) {
        PersonInMemoryDB.INSTANCE.add(personDto);
    }

    @GetMapping("/findAll")
    public List<PersonDto> findAll() {
        return PersonInMemoryDB.INSTANCE.findAll();
    }

    @GetMapping("/findById/{personid}")
    public PersonDto findById(@PathVariable("personid") Integer personid) {
        return PersonInMemoryDB.INSTANCE.findById(personid);
    }

    @PostMapping("/remove/{personid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable("personid") Integer personid) {
        PersonInMemoryDB.INSTANCE.remove(personid);
    }

    @PostMapping("/edit")
    public void edit(@RequestBody PersonDto personDto) {
        PersonInMemoryDB.INSTANCE.edit(personDto);
    }
}
