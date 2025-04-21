package com.seek.seekchallenge.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResourcePath {

   public static final String BASE_PATH_API = "/api";

   public static final String BASE_PATH_AUTH = "/auth";

   public static final String ENDPOINT_CANDIDATE = "/candidate";

   public static final String ENDPOINT_CANDIDATE_ID = "/candidate/{id}";

   public static final String ENDPOINT_GENERATE_TOKEN = "/generateToken";

}
