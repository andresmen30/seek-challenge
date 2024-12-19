package com.seek.seekchallenge.domain.exception;

import java.io.Serializable;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class EnumBadRequestException extends RuntimeException implements Serializable {

   @Builder.Default
   private String message = "the value must be %s";

   public EnumBadRequestException(final String value) {
      this.message = String.format(this.message, value);
   }

}
