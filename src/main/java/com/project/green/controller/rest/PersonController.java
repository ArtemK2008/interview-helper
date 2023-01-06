package com.project.green.controller.rest;

import com.project.green.dto.PersonDto;
import com.project.green.security.CustomUserDetails;
import com.project.green.service.PersonService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/person")
@Tag(name = "Person Controller", description = "controller for operation by person")
public class PersonController {

    @Autowired
    private PersonService personService;


    @GetMapping("")
    public PersonDto getPerson(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return personService.getByEmail(userDetails.getUsername());
    }

    @PutMapping("/update")
    public PersonDto update(@RequestBody PersonDto personDto) {
        return personService.update(personDto);
    }

}
