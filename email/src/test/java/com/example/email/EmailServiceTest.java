package com.example.email;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmailServiceTest {

  @Autowired private EmailService emailService;

  @BeforeAll
  static void rebuildSchema(@Autowired EmailModule emailModule) {
    emailModule.rebuildSchema();
  }

  @Test
  void doSomethingTest() {
    emailService.doSomething();
  }

  @Test
  void doAnotherThing() {
    emailService.doSomething();
  }
}
