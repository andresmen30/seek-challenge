package com.seek.seekchallenge.infraestructure.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessTokenDto {

   private String accessToken;

   private LocalDateTime expiration;

}
