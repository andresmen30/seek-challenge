package com.seek.seekchallenge.application.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.seek.seekchallenge.application.util.ResponseUtil;
import com.seek.seekchallenge.domain.service.CandidateService;
import com.seek.seekchallenge.infraestructure.dto.CandidateDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebMvcTest(CandidateController.class)
@TestPropertySource(locations = "classpath:application.properties")
class CandidateControllerTest {

   @Autowired
   private MockMvc mockMvc;

   @MockBean
   private CandidateService candidateService;

   @Value("${rest.request.mapping}")
   private String restUri;

   @Value("${endpoint.candidate}")
   private String endpointCandidate;

   @Value("${endpoint.candidate.id}")
   private String endpointCandidateId;

   private CandidateDto candidateDto;

   @BeforeEach
   public void setup() {
      candidateDto = CandidateDto
            .builder()
            .id(8)
            .email("dsa.martinez@gmail.com")
            .gender("FEMALE")
            .salaryExpected(NumberUtils.createBigDecimal("1200"))
            .name("Jose Martinez")
            .build();
   }

   @Test
   void saveCandidate() throws Exception {
      log.info("(saveCandidate) request: {}", candidateDto);
      when(candidateService.save(any(), any())).thenReturn(candidateDto);
      mockMvc
            .perform(post(StringUtils.join(restUri, endpointCandidate))
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(ResponseUtil.objectToJsonString(candidateDto)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.message", is(HttpStatus.CREATED.name())))
            .andExpect(jsonPath("$.time", notNullValue()))
            .andExpect(jsonPath("$.data", notNullValue()))
            .andExpect(jsonPath("$.data.id", is(candidateDto.getId())))
            .andExpect(jsonPath("$.data.name", is(candidateDto.getName())))
            .andExpect(jsonPath("$.data.gender", is(candidateDto.getGender())))
            .andExpect(jsonPath("$.data.email", is(candidateDto.getEmail())))
            .andExpect(jsonPath("$.data.salaryExpected", is(candidateDto.getSalaryExpected().intValue())));
      log.info("(saveCandidate) [[end]]");
   }

   @Test
   void updateCandidate() throws Exception {
      log.info("(updateCandidate) request: {}", candidateDto);
      when(candidateService.save(any(), any())).thenReturn(candidateDto);
      mockMvc
            .perform(put(StringUtils.join(restUri, endpointCandidateId), 8)
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(ResponseUtil.objectToJsonString(candidateDto)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.message", is(HttpStatus.OK.name())))
            .andExpect(jsonPath("$.time", notNullValue()))
            .andExpect(jsonPath("$.data", notNullValue()))
            .andExpect(jsonPath("$.data.id", is(candidateDto.getId())))
            .andExpect(jsonPath("$.data.name", is(candidateDto.getName())))
            .andExpect(jsonPath("$.data.gender", is(candidateDto.getGender())))
            .andExpect(jsonPath("$.data.email", is(candidateDto.getEmail())))
            .andExpect(jsonPath("$.data.salaryExpected", is(candidateDto.getSalaryExpected().intValue())));
      log.info("(updateCandidate) [[end]]");
   }

   @Test
   void deleteCandidate() throws Exception {
      log.info("(deleteCandidate)");
      mockMvc
            .perform(delete(StringUtils.join(restUri, endpointCandidateId), 8).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isAccepted())
            .andExpect(jsonPath("$.message", is(HttpStatus.ACCEPTED.name())))
            .andExpect(jsonPath("$.time", notNullValue()));
      log.info("(deleteCandidate) [[end]]");
   }

   @Test
   void findAll() throws Exception {
      log.info("(findAll)");
      when(candidateService.findAll()).thenReturn(List.of(candidateDto));
      mockMvc
            .perform(get(StringUtils.join(restUri, endpointCandidate)).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.message", is(HttpStatus.OK.name())))
            .andExpect(jsonPath("$.data", notNullValue()))
            .andExpect(jsonPath("$.data").isArray())
            .andExpect(jsonPath("$.data").value(hasSize(NumberUtils.INTEGER_ONE)));
      log.info("(findAll) [[end]]");
   }

   @Test
   void findById() throws Exception {
      when(candidateService.findById(any())).thenReturn(candidateDto);
      mockMvc
            .perform(get(StringUtils.join(restUri, endpointCandidateId), 8).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.message", is(HttpStatus.OK.name())))
            .andExpect(jsonPath("$.time", notNullValue()))
            .andExpect(jsonPath("$.data", notNullValue()))
            .andExpect(jsonPath("$.data.id", is(candidateDto.getId())))
            .andExpect(jsonPath("$.data.name", is(candidateDto.getName())))
            .andExpect(jsonPath("$.data.gender", is(candidateDto.getGender())))
            .andExpect(jsonPath("$.data.email", is(candidateDto.getEmail())))
            .andExpect(jsonPath("$.data.salaryExpected", is(candidateDto.getSalaryExpected().intValue())));
      log.info("(findAll) [[end]]");
   }

}