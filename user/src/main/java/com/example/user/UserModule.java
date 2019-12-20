package com.example.user;

import com.example.Module;
import org.springframework.stereotype.Component;

@Component
public class UserModule implements Module {

  @Override
  public String getName() {
    return "user";
  }
}
