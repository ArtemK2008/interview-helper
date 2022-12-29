package com.project.green.mapper;

import com.project.green.dto.PersonDto;
import com.project.green.entities.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonDto toPersonDto(Person person);

    Person toPerson(PersonDto personDto);
}
