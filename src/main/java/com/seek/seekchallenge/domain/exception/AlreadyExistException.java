package com.seek.seekchallenge.domain.exception;

import java.io.Serializable;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class AlreadyExistException extends RuntimeException implements Serializable {

   @Builder.Default
   private String message = "the %s: %s already exist";

   public AlreadyExistException(final String field, final Object value) {
      this.message = String.format(this.message, field, value);
   }

}
