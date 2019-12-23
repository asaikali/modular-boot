package com.example.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public EmailService(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public void doSomething() {
    this.jdbcTemplate.execute("SELECT 1");
  }
}
