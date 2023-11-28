package com.bits.bids.config;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  @Bean
  public StrongPasswordEncryptor strongPasswordEncryptor() {
    return new StrongPasswordEncryptor();
  }

}
