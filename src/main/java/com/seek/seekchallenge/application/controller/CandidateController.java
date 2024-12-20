package com.seek.seekchallenge.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.seek.seekchallenge.application.util.ResponseUtil;
import com.seek.seekchallenge.domain.service.CandidateService;
import com.seek.seekchallenge.infraestructure.dto.CandidateDto;
import com.seek.seekchallenge.infraestructure.dto.ResponseDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Candidate", description = "CRUD")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "${rest.request.mapping}")
public class CandidateController {

   private final CandidateService candidateService;

   @GetMapping(value = "${endpoint.candidate}")
   @ResponseStatus(HttpStatus.OK)
   @Operation(summary = "get all candidates", description = "gets all candidates in a list")
   public @ResponseBody ResponseDto findAll() {
      return ResponseUtil.response(HttpStatus.OK, candidateService.findAll());
   }

   @GetMapping(value = "${endpoint.candidate.id}")
   @ResponseStatus(HttpStatus.OK)
   @Operation(summary = "get candidate by id", description = "gets a single candidate filtered by id")
   public @ResponseBody ResponseDto candidateById(@Parameter(description = "key id candidate", required = true) @PathVariable final Integer id) {
      return ResponseUtil.response(HttpStatus.OK, candidateService.findById(id));
   }

   @PostMapping(value = "${endpoint.candidate}")
   @ResponseStatus(HttpStatus.CREATED)
   @Operation(summary = "create candidate", description = "create a new candidate")
   public @ResponseBody ResponseDto saveCandidate(@Valid @RequestBody final CandidateDto candidateDto) {
      return ResponseUtil.response(HttpStatus.CREATED, candidateService.save(null, candidateDto));
   }

   @PutMapping(value = "${endpoint.candidate.id}")
   @Operation(summary = "update candidate", description = "update a candidate")
   @ResponseStatus(HttpStatus.OK)
   public @ResponseBody ResponseDto updateCandidate(@Parameter(description = "key id candidate", required = true) @PathVariable final Integer id,
         @Valid @RequestBody final CandidateDto candidateDto) {
      return ResponseUtil.response(HttpStatus.OK, candidateService.save(id, candidateDto));
   }

   @DeleteMapping(value = "${endpoint.candidate.id}")
   @Operation(summary = "delete candidate", description = "delete a candidate")
   @ResponseStatus(HttpStatus.ACCEPTED)
   public @ResponseBody ResponseDto deleteCandidate(@Parameter(description = "key id candidate", required = true) @PathVariable final Integer id) {
      candidateService.delete(id);
      return ResponseUtil.response(HttpStatus.ACCEPTED, null);
   }

}