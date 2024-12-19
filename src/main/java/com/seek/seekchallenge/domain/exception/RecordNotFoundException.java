package com.seek.seekchallenge.domain.exception;

import java.io.Serializable;
import java.util.Arrays;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class RecordNotFoundException extends RuntimeException implements Serializable {

   @Builder.Default
   private String message = "the %s not found";

   public RecordNotFoundException(Object... values) {
      this.message = String.format(this.message, Arrays.stream(values).map(Object::toString).collect(Collectors.joining(",")));
   }

}
