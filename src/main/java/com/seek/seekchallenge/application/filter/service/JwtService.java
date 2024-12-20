package com.seek.seekchallenge.application.filter.service;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.seek.seekchallenge.infraestructure.dto.AccessTokenDto;
import com.seek.seekchallenge.infraestructure.dto.UserInfoDto;

import lombok.RequiredArgsConstructor;

@Service
@Lazy
@RequiredArgsConstructor
public class JwtService {

   private final AuthenticationManager authenticationManager;

   @Value("${token.expiration}")
   private Long tokenExpiration;

   @Value("${token.secret.key}")
   private String secretKey;

   public AccessTokenDto authenticate(UserInfoDto userInfo) {
      Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(userInfo.getUsername(), userInfo.getPassword()));
      if (authentication.isAuthenticated()) {
         final String token = generateToken(userInfo.getUsername());
         final LocalDateTime expiration = extractExpiration(token).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
         return AccessTokenDto.builder().accessToken(token).expiration(expiration).build();
      } else {
         throw new UsernameNotFoundException("Invalid user request!");
      }
   }

   private String generateToken(String userName) {
      Map<String, Object> claims = new HashMap<>();
      return createToken(claims, userName);
   }

   private String createToken(Map<String, Object> claims, String userName) {
      return Jwts
            .builder()
            .setClaims(claims)
            .setSubject(userName)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
            .signWith(getSignKey(), SignatureAlgorithm.HS256)
            .compact();
   }

   private Key getSignKey() {
      byte[] keyBytes = Decoders.BASE64.decode(secretKey);
      return Keys.hmacShaKeyFor(keyBytes);
   }

   public String extractUsername(String token) {
      return extractClaim(token, Claims::getSubject);
   }

   public Date extractExpiration(String token) {
      return extractClaim(token, Claims::getExpiration);
   }

   public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
      final Claims claims = extractAllClaims(token);
      return claimsResolver.apply(claims);
   }

   private Claims extractAllClaims(String token) {
      return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
   }

   private Boolean isTokenExpired(String token) {
      return extractExpiration(token).before(new Date());
   }

   public Boolean validateToken(String token, UserDetails userDetails) {
      final String username = extractUsername(token);
      return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
   }

}
