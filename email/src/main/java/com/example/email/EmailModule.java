package com.example.email;

import com.example.Module;
import org.springframework.stereotype.Component;

@Component
public class EmailModule implements Module {

  @Override
  public String getName() {
    return "email";
  }
}
