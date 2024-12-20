package com.seek.seekchallenge.infraestructure.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDto {

   @NotNull
   @NotBlank
   private String username;

   @NotNull
   @NotBlank
   private String password;

}
