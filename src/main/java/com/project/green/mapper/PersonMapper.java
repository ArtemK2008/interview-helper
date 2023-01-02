package com.project.green.mapper;

import com.project.green.dto.PersonDto;
import com.project.green.entities.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    @Mapping(target = "statisticsId", source = "statistics.personId")
    PersonDto toPersonDto(Person person);

    @Mapping(source = "statisticsId", target = "statistics.personId")
    Person toPerson(PersonDto personDto);
}
