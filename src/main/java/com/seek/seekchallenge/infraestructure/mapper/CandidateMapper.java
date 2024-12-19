package com.seek.seekchallenge.infraestructure.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.seek.seekchallenge.infraestructure.dto.CandidateDto;
import com.seek.seekchallenge.infraestructure.entity.Candidate;
import com.seek.seekchallenge.infraestructure.gender.GenderEnum;

@Mapper(componentModel = "spring", imports = GenderEnum.class)
public interface CandidateMapper {

   @Mapping(target = "gender", expression = "java(GenderEnum.enumToString(entity.getGender()))")
   CandidateDto toDto(final Candidate entity);

   @Mapping(target = "gender", expression = "java(GenderEnum.stringToEnum(dto.getGender()))")
   Candidate toEntity(final CandidateDto dto);

   List<CandidateDto> toDto(final List<Candidate> entity);

}
