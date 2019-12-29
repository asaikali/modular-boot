package com.example.email;

import com.example.Module;
import javax.sql.DataSource;
import org.springframework.stereotype.Component;

@Component
public class EmailModule extends Module {

  EmailModule(DataSource dataSource) {
    super(dataSource, "email");
  }
}
