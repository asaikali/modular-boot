package com.example.email;

import com.example.ModuleConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@DataJpaTest
@ContextConfiguration(classes = ModuleConfiguration.class)
class ModularBootApplicationTests {

  @Test
  void contextLoads() {}
}
