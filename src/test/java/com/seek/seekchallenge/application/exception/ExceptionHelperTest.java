package com.seek.seekchallenge.application.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.seek.seekchallenge.domain.exception.AlreadyExistException;
import com.seek.seekchallenge.domain.exception.RecordNotFoundException;
import com.seek.seekchallenge.infraestructure.dto.ResponseDto;
import com.seek.seekchallenge.util.ConstantUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(MockitoExtension.class)
class ExceptionHelperTest {

   @InjectMocks
   private ExceptionHelper exceptionHelper;

   @BeforeEach
   void before() {
      log.info("#############################################################################");
   }

   @Test
   void recordNotFound() {
      final ResponseDto response = exceptionHelper.recordNotFound(new RecordNotFoundException(ConstantUtil.FIELD_ID));
      log.info("(recordNotFound) response: {}", response);
      assertNotNull(response);
      assertNotNull(response.getData());
      assertNotNull(response.getTime());
      assertEquals(HttpStatus.NOT_FOUND.name(), response.getMessage());
      log.info("(recordNotFound) [[end]]");
   }

   @Test
   void alreadyExistException() {
      final ResponseDto response = exceptionHelper.alreadyExist(new AlreadyExistException(ConstantUtil.FIELD_EMAIL, "andres.mendez@gmail.com"));
      log.info("(alreadyExistException) response: {}", response);
      assertNotNull(response);
      assertNotNull(response.getData());
      assertNotNull(response.getTime());
      assertEquals(HttpStatus.BAD_REQUEST.name(), response.getMessage());
      log.info("(alreadyExistException) [[end]]");
   }

}