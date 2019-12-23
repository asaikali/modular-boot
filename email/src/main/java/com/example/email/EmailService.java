package com.example.email;

import org.springframework.jdbc.core.JdbcTemplate;

public class EmailService {

  private final JdbcTemplate jdbcTemplate;

  public EmailService(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public void doSomething() {
    this.jdbcTemplate.execute("SELECT 1");
  }
}
