package com.seek.seekchallenge.infraestructure.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Schema(description = "Dto model Candidate")
public class CandidateDto {

   @Schema(description = "id candidate")
   @JsonProperty(access = JsonProperty.Access.READ_ONLY)
   private Integer id;

   @Schema(description = "name candidate")
   @NotBlank
   private String name;

   @Schema(description = "email")
   @NotBlank
   @Email(message = "Email must be valid")
   private String email;

   @Schema(description = "gender")
   @NotNull
   @Pattern(regexp = "(?i)FEMALE|MALE|OTHER", message = "must match FEMALE, MALE, OTHER")
   private String gender;

   @Schema(description = "salary expected")
   @NotNull
   @DecimalMin(value = "0.01")
   private BigDecimal salaryExpected;

}
