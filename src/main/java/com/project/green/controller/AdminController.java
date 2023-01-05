package com.project.green.controller;

import com.project.green.dto.PersonDto;
import com.project.green.service.PersonService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/admin")
@Tag(name = "Admin Controller", description = "controller for admin operations")
public class AdminController {

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

    @DeleteMapping("/delete")
    public void delete(@RequestBody PersonDto personDto) {
        personService.deleteById(personDto.getId());
    }
}
