package com.project.green.mapper;

import com.project.green.dto.StatisticsDto;
import com.project.green.entities.Statistics;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StatisticsMapper {

    StatisticsDto toStatisticsDto(Statistics statistics);

    Statistics toStatistics(StatisticsDto statisticsDto);

}
