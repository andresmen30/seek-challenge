package com.seek.seekchallenge.infraestructure.enums;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter(AccessLevel.PUBLIC)
@AllArgsConstructor
public enum GenderEnum {

   FEMALE,
   MALE,
   OTHER;

   public static GenderEnum stringToEnum(final String gender) {
      return GenderEnum.valueOf(StringUtils.upperCase(gender, Locale.getDefault()));
   }

   public static String enumToString(final GenderEnum genderEnum) {
      return genderEnum.name();
   }

}
