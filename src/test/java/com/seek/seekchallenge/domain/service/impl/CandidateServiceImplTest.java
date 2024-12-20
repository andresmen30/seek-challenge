package com.seek.seekchallenge.domain.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.seek.seekchallenge.domain.exception.AlreadyExistException;
import com.seek.seekchallenge.domain.exception.RecordNotFoundException;
import com.seek.seekchallenge.infraestructure.dto.CandidateDto;
import com.seek.seekchallenge.infraestructure.entity.Candidate;
import com.seek.seekchallenge.infraestructure.enums.GenderEnum;
import com.seek.seekchallenge.infraestructure.mapper.CandidateMapper;
import com.seek.seekchallenge.infraestructure.repository.CandidateRepository;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = CandidateRepository.class)
class CandidateServiceImplTest {

   @Mock
   private CandidateRepository candidateRepository;

   @Mock
   private CandidateMapper candidateMapper;

   @InjectMocks
   private CandidateServiceImpl candidateService;

   private Candidate candidate;

   @BeforeEach
   public void setup() {
      candidate = Candidate
            .builder()
            .id(8)
            .email("dsa.martinez@gmail.com")
            .gender(GenderEnum.OTHER)
            .salaryExpected(NumberUtils.createBigDecimal("1400"))
            .name("pepe test service")
            .build();
   }

   @Test
   void findAll() {
      final List<Candidate> listEntity = List.of(candidate);
      final List<CandidateDto> listDto = List.of(CandidateDto
            .builder()
            .id(candidate.getId())
            .name(candidate.getName())
            .salaryExpected(candidate.getSalaryExpected())
            .email(candidate.getEmail())
            .gender(GenderEnum.OTHER.name())
            .build());
      given(candidateRepository.findAll()).willReturn(listEntity);
      given(candidateMapper.toDto(listEntity)).willReturn(listDto);
      final List<CandidateDto> listCandidates = candidateService.findAll();
      assertThat(listCandidates).isNotNull();
      assertThat(listCandidates.size()).isEqualTo(NumberUtils.INTEGER_ONE);

   }

   @Test
   void findById() {
      final CandidateDto candidateDto = CandidateDto
            .builder()
            .id(candidate.getId())
            .name(candidate.getName())
            .salaryExpected(candidate.getSalaryExpected())
            .email(candidate.getEmail())
            .gender(GenderEnum.OTHER.name())
            .build();
      given(candidateRepository.findById(candidate.getId())).willReturn(Optional.of(candidate));
      given(candidateMapper.toDto(candidate)).willReturn(candidateDto);
      final CandidateDto candidateFindById = candidateService.findById(candidate.getId());
      assertThat(candidateFindById).isNotNull();
   }

   @Test
   void findByIdRecordNotFoundException() {
      given(candidateRepository.findById(candidate.getId())).willReturn(Optional.empty());
      assertThrows(RecordNotFoundException.class, () -> candidateService.findById(candidate.getId()));
      verify(candidateRepository).findById(candidate.getId());
      verifyNoMoreInteractions(candidateRepository);

   }

   @Test
   void saveAlreadyExistException() {
      final CandidateDto candidateDto = CandidateDto
            .builder()
            .id(candidate.getId())
            .name(candidate.getName())
            .salaryExpected(candidate.getSalaryExpected())
            .email(candidate.getEmail())
            .gender(GenderEnum.OTHER.name())
            .build();
      given(candidateMapper.toEntity(candidateDto)).willReturn(candidate);
      given(candidateRepository.findCandidateByEmail(candidate.getEmail())).willReturn(Optional.of(candidate));
      assertThrows(AlreadyExistException.class, () -> candidateService.save(null, candidateDto));
      verify(candidateRepository).findCandidateByEmail(candidate.getEmail());
      verifyNoMoreInteractions(candidateRepository);
   }

   @Test
   void saveRecordNotFoundException() {
      final CandidateDto candidateDto = CandidateDto
            .builder()
            .id(candidate.getId())
            .name(candidate.getName())
            .salaryExpected(candidate.getSalaryExpected())
            .email(candidate.getEmail())
            .gender(GenderEnum.OTHER.name())
            .build();
      given(candidateMapper.toEntity(candidateDto)).willReturn(candidate);
      given(candidateRepository.findById(candidate.getId())).willReturn(Optional.empty());
      assertThrows(RecordNotFoundException.class, () -> candidateService.save(candidate.getId(), candidateDto));
      verify(candidateRepository).findById(candidate.getId());
      verifyNoMoreInteractions(candidateRepository);
   }

   @Test
   void deleteRecordNotFoundException() {
      given(candidateRepository.findById(candidate.getId())).willReturn(Optional.empty());
      assertThrows(RecordNotFoundException.class, () -> candidateService.delete(candidate.getId()));
      verify(candidateRepository).findById(candidate.getId());
      verifyNoMoreInteractions(candidateRepository);
   }

   @Test
   void delete() {
      given(candidateRepository.findById(candidate.getId())).willReturn(Optional.of(candidate));
      candidateService.delete(candidate.getId());
      verify(candidateRepository, times(1)).delete(candidate);
   }

}