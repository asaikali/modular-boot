package com.example.user;

import com.example.core.db.Module;
import javax.sql.DataSource;
import org.springframework.stereotype.Component;

@Component
class UserModule extends Module {
  UserModule(DataSource dataSource) {
    super(dataSource, "user");
  }
}
