package com.seek.seekchallenge.infraestructure.entity;

import java.math.BigDecimal;

import com.seek.seekchallenge.infraestructure.gender.GenderEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Candidate {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   private String name;

   private String email;

   @Enumerated(EnumType.STRING)
   private GenderEnum gender;

   private BigDecimal salaryExpected;

}
