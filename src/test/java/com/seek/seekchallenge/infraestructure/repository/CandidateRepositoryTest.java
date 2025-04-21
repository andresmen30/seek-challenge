package com.seek.seekchallenge.infraestructure.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.seek.seekchallenge.infraestructure.entity.Candidate;
import com.seek.seekchallenge.infraestructure.enums.GenderEnum;

import lombok.extern.slf4j.Slf4j;

@ActiveProfiles("test")
@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CandidateRepositoryTest {

   @Autowired
   private CandidateRepository candidateRepository;

   private Candidate saveCandidate;

   @BeforeEach
   void setUp() {
      log.info("(setUp) insert data test");
      saveCandidate = Candidate
            .builder()
            .email("test@gmail.com")
            .name("Test insert")
            .gender(GenderEnum.OTHER)
            .salaryExpected(NumberUtils.createBigDecimal("1200"))
            .build();
      candidateRepository.save(saveCandidate);
      log.info("(setUp) end insert data test");
   }

   @AfterEach
   void tearDown() {
      log.info("(setUp) delete data test");
      candidateRepository.delete(saveCandidate);
      log.info("(setUp) delete data test");
   }

   @Test
   void givenCandidate_whenSaved_thenCanBeFoundById() {
      log.info("(givenCandidate_whenSaved_thenCanBeFoundById)");
      Candidate candidateFindById = candidateRepository.findById(saveCandidate.getId()).orElse(null);
      assertNotNull(candidateFindById);
      assertEquals(candidateFindById.getEmail(), saveCandidate.getEmail());
      assertEquals(candidateFindById.getName(), saveCandidate.getName());
      assertEquals(candidateFindById.getSalaryExpected(), saveCandidate.getSalaryExpected());
      assertEquals(candidateFindById.getGender(), saveCandidate.getGender());
      log.info("(end)");
   }

   @Test
   void givenCandidate_whenUpdated_thenCanBeFoundByIdWithUpdatedData() {
      log.info("(givenCandidate_whenUpdated_thenCanBeFoundByIdWithUpdatedData)");
      saveCandidate.setName("Test update");
      candidateRepository.save(saveCandidate);

      Candidate updatedCandidate = candidateRepository.findById(saveCandidate.getId()).orElse(null);

      assertNotNull(updatedCandidate);
      assertEquals("Test update", updatedCandidate.getName());
      log.info("(end)");
   }

   @Test
   void findCandidate_ByEmail_CaseSensitive() {
      log.info("(findCandidate_ByEmail_CaseSensitive)");
      Candidate updatedCandidate = candidateRepository.findCandidateByEmail("tEsT@GmaiL.Com").orElse(null);
      assertNotNull(updatedCandidate);
      log.info("(end)");
   }

   @Test
   void findCandidate_ByEmail_WithoutId_CaseSensitive() {
      log.info("(findCandidate_ByEmail_WithoutId_CaseSensitive)");
      Candidate updatedCandidate = candidateRepository.findCandidateByEmailWithoutId("tEsT@GmaiL.Com", saveCandidate.getId()).orElse(null);
      assertNull(updatedCandidate);
      log.info("(end)");
   }

}