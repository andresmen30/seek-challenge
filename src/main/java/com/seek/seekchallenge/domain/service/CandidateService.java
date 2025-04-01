package com.seek.seekchallenge.domain.service;

import java.util.List;

import com.seek.seekchallenge.infraestructure.dto.CandidateDto;

public interface CandidateService {

   List<CandidateDto> findAll();

   CandidateDto findById(Integer id);

   CandidateDto save(final Integer id, final CandidateDto dto);

   void delete(final Integer id);

}
