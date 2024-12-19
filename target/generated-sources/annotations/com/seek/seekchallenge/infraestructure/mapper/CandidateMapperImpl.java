package com.seek.seekchallenge.infraestructure.mapper;

import com.seek.seekchallenge.infraestructure.dto.CandidateDto;
import com.seek.seekchallenge.infraestructure.entity.Candidate;
import com.seek.seekchallenge.infraestructure.gender.GenderEnum;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-19T01:43:48-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.13 (Amazon.com Inc.)"
)
@Component
public class CandidateMapperImpl implements CandidateMapper {

    @Override
    public CandidateDto toDto(Candidate entity) {
        if ( entity == null ) {
            return null;
        }

        CandidateDto.CandidateDtoBuilder candidateDto = CandidateDto.builder();

        candidateDto.id( entity.getId() );
        candidateDto.name( entity.getName() );
        candidateDto.email( entity.getEmail() );
        candidateDto.salaryExpected( entity.getSalaryExpected() );

        candidateDto.gender( GenderEnum.enumToString(entity.getGender()) );

        return candidateDto.build();
    }

    @Override
    public Candidate toEntity(CandidateDto dto) {
        if ( dto == null ) {
            return null;
        }

        Candidate.CandidateBuilder candidate = Candidate.builder();

        candidate.id( dto.getId() );
        candidate.name( dto.getName() );
        candidate.email( dto.getEmail() );
        candidate.salaryExpected( dto.getSalaryExpected() );

        candidate.gender( GenderEnum.stringToEnum(dto.getGender()) );

        return candidate.build();
    }

    @Override
    public List<CandidateDto> toDto(List<Candidate> entity) {
        if ( entity == null ) {
            return null;
        }

        List<CandidateDto> list = new ArrayList<CandidateDto>( entity.size() );
        for ( Candidate candidate : entity ) {
            list.add( toDto( candidate ) );
        }

        return list;
    }
}
