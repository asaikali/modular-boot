package com.example.email;

import com.example.Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class EmailModule implements Module {

  @Override
  public String getName() {
    return "email";
  }

  @Bean
  public EmailService emailService(JdbcTemplate jdbcTemplate) {
    return new EmailService(jdbcTemplate);
  }
}
