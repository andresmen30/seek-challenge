package com.seek.seekchallenge.infraestructure.config;

import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
public class JsonConfig {

   private ObjectMapper createObjectMapper() {
      final ObjectMapper mapper = new ObjectMapper();
      mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
      mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
      return mapper;
   }

}
