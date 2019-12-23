package com.example.email;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmailServiceTest {

  @Autowired private EmailService emailService;

  @Test
  void doSomethingTest() {
    emailService.doSomething();
  }
}
