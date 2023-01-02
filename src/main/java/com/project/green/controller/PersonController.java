package com.project.green.controller;

import com.project.green.dto.PersonDto;
import com.project.green.service.PersonService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/person")
@Tag(name = "Person Controller", description = "controller for operation by person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/create")
    public void create(@RequestBody PersonDto personDto) {
        personService.save(personDto);
    }

    @GetMapping("/all")
    public List<PersonDto> findAll() {
        return personService.getAll();
    }

    @GetMapping("/{id}")
    public PersonDto findById(@PathVariable("id") int id) {
        return personService.getById(id);
    }

    @PutMapping("/update")
    public PersonDto update(@RequestBody PersonDto personDto) {
        return personService.update(personDto);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody PersonDto personDto) {
        personService.deleteById(personDto.getId());
    }
}
