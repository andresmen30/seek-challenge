package com.seek.seekchallenge.application.exception;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.jsonwebtoken.ExpiredJwtException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.seek.seekchallenge.application.util.ResponseUtil;
import com.seek.seekchallenge.domain.exception.AlreadyExistException;
import com.seek.seekchallenge.domain.exception.EnumBadRequestException;
import com.seek.seekchallenge.domain.exception.RecordNotFoundException;
import com.seek.seekchallenge.infraestructure.dto.ResponseDto;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionHelper {

   @ExceptionHandler(MethodArgumentNotValidException.class)
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   protected @ResponseBody ResponseDto handleMethodArgumentNotValid(final MethodArgumentNotValidException ex) {
      final List<String> details = new ArrayList<>();
      ex.getBindingResult().getAllErrors().forEach(error -> {
         final String detail = StringUtils.join(((FieldError) error).getField(), " : ", Objects.requireNonNull(error.getDefaultMessage()));
         details.add(detail);
      });
      return ResponseUtil.response(HttpStatus.BAD_REQUEST, details.toString());
   }

   @ExceptionHandler(ConstraintViolationException.class)
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   protected @ResponseBody ResponseDto handleConstraintViolationException(final ConstraintViolationException ex) {
      final List<String> details = new ArrayList<>();
      ex.getConstraintViolations().forEach(constraintViolation -> {
         final String detail = StringUtils.join(constraintViolation.getPropertyPath(), " : ", constraintViolation.getMessage());
         details.add(detail);
      });
      return ResponseUtil.response(HttpStatus.BAD_REQUEST, details.toString());
   }

   @ExceptionHandler(MethodArgumentTypeMismatchException.class)
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   protected @ResponseBody ResponseDto handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException ex) {
      return ResponseUtil.response(HttpStatus.BAD_REQUEST, ex.getMessage());
   }

   @ExceptionHandler(MissingServletRequestParameterException.class)
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   protected @ResponseBody ResponseDto handleMissingServletRequestParameter(final MissingServletRequestParameterException ex) {
      return ResponseUtil.response(HttpStatus.BAD_REQUEST, ex.getMessage());
   }

   @ExceptionHandler(SQLException.class)
   @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
   protected @ResponseBody ResponseDto handleSqlException(final SQLException ex) {
      return ResponseUtil.response(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
   }

   @ExceptionHandler(InvalidDataAccessApiUsageException.class)
   @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
   protected @ResponseBody ResponseDto handleInvalidAccessApi(final InvalidDataAccessApiUsageException ex) {
      return ResponseUtil.response(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());

   }

   @ExceptionHandler(HttpMessageNotReadableException.class)
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   protected @ResponseBody ResponseDto handleMessageReadable(final HttpMessageNotReadableException ex) {
      return ResponseUtil.response(HttpStatus.BAD_REQUEST, ex.getMessage());

   }

   @ExceptionHandler(RecordNotFoundException.class)
   @ResponseStatus(HttpStatus.NOT_FOUND)
   protected @ResponseBody ResponseDto recordNotFound(final RecordNotFoundException ex) {
      return ResponseUtil.response(HttpStatus.NOT_FOUND, ex.getMessage());
   }

   @ExceptionHandler(AlreadyExistException.class)
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   protected @ResponseBody ResponseDto alreadyExist(final AlreadyExistException ex) {
      return ResponseUtil.response(HttpStatus.BAD_REQUEST, ex.getMessage());
   }

   @ExceptionHandler(EnumBadRequestException.class)
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   protected @ResponseBody ResponseDto enumBadRequest(final EnumBadRequestException ex) {
      return ResponseUtil.response(HttpStatus.BAD_REQUEST, ex.getMessage());
   }

   @ExceptionHandler(AuthenticationException.class)
   @ResponseStatus(HttpStatus.UNAUTHORIZED)
   protected @ResponseBody ResponseDto handleException(final AuthenticationException ex) {
      return ResponseUtil.response(HttpStatus.UNAUTHORIZED, ex.getMessage());
   }

   @ExceptionHandler(ExpiredJwtException.class)
   @ResponseStatus(HttpStatus.UNAUTHORIZED)
   protected @ResponseBody ResponseDto jsonWebTokenException(final ExpiredJwtException ex) {
      return ResponseUtil.response(HttpStatus.UNAUTHORIZED, ex.getMessage());
   }

   @ExceptionHandler(Exception.class)
   @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
   protected @ResponseBody ResponseDto handleException(final Exception ex) {
      return ResponseUtil.response(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
   }

}
