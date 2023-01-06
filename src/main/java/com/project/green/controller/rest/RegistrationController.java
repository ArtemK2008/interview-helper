package com.project.green.controller.rest;

import com.project.green.dto.PersonDto;
import com.project.green.service.PersonService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/registration")
@Tag(name = "Question Controller", description = "controller for unregistered users")
public class RegistrationController {

    @Autowired
    PersonService personService;

    @PostMapping()
    public void create(@RequestBody @Valid PersonDto personDto) {
        personService.save(personDto);
    }

}
