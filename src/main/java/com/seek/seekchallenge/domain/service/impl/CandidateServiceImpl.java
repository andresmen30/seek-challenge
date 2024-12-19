package com.seek.seekchallenge.domain.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.seek.seekchallenge.domain.exception.AlreadyExistException;
import com.seek.seekchallenge.domain.exception.RecordNotFoundException;
import com.seek.seekchallenge.domain.service.CandidateService;
import com.seek.seekchallenge.infraestructure.dto.CandidateDto;
import com.seek.seekchallenge.infraestructure.entity.Candidate;
import com.seek.seekchallenge.infraestructure.mapper.CandidateMapper;
import com.seek.seekchallenge.infraestructure.repository.CandidateRepository;
import com.seek.seekchallenge.util.ConstantUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

   private final CandidateRepository candidateRepository;

   private final CandidateMapper candidateMapper;

   @Override
   public List<CandidateDto> findAll() {
      return candidateMapper.toDto(candidateRepository.findAll());
   }

   @Override
   public CandidateDto findById(Integer id) {
      final Candidate candidate = candidateRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(ConstantUtil.FIELD_ID, id));
      return candidateMapper.toDto(candidate);
   }

   @Override
   public CandidateDto save(final Integer id, final CandidateDto dto) {
         final Candidate candidate = candidateMapper.toEntity(dto);
      candidate.setId(id);
      if (id == null) {
         if (candidateRepository.findCandidateByEmail(dto.getEmail()).isPresent()) {
            throw new AlreadyExistException(ConstantUtil.FIELD_EMAIL, dto.getEmail());
         }
      } else {
         candidateRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(ConstantUtil.FIELD_ID, id));
         if (candidateRepository.findCandidateByEmailWithoutId(dto.getEmail(), id).isPresent()) {
            throw new AlreadyExistException(ConstantUtil.FIELD_EMAIL, dto.getEmail());
         }
      }
      return candidateMapper.toDto(candidateRepository.save(candidate));
   }

   @Override
   public void delete(final Integer id) {
      final Candidate candidate = candidateRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(ConstantUtil.FIELD_ID, id));
      candidateRepository.delete(candidate);
   }

}
