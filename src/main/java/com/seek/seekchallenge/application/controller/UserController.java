package com.seek.seekchallenge.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.seek.seekchallenge.application.filter.service.JwtService;
import com.seek.seekchallenge.infraestructure.dto.AccessTokenDto;
import com.seek.seekchallenge.infraestructure.dto.UserInfoDto;
import com.seek.seekchallenge.util.ResourcePath;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "User", description = "TOKEN")
@RestController
@RequiredArgsConstructor
@RequestMapping(ResourcePath.BASE_PATH_AUTH)
public class UserController {

   private final JwtService jwtService;

   @PostMapping(ResourcePath.ENDPOINT_GENERATE_TOKEN)
   @ResponseStatus(HttpStatus.OK)
   @Operation(summary = "generate token", description = "generate token")
   public AccessTokenDto authenticateAndGetToken(@Valid @RequestBody UserInfoDto userInfo) {
      return jwtService.authenticate(userInfo);
   }

}
