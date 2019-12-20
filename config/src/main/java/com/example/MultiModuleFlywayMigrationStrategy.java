package com.example;

import java.util.List;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.stereotype.Component;

@Component
public class MultiModuleFlywayMigrationStrategy implements FlywayMigrationStrategy {

  private final List<Module> modules;

  @Autowired
  public MultiModuleFlywayMigrationStrategy(List<Module> modules) {
    this.modules = modules;
  }

  @Override
  public void migrate(Flyway flyway) {
    var dataSource = flyway.getConfiguration().getDataSource();

    for (var module : modules) {
      Flyway flywayModule =
          Flyway.configure()
              .schemas(module.getName())
              .locations("db/" + module.getName())
              .dataSource(dataSource)
              .load();
      flywayModule.migrate();
    }

    try {
      flyway.migrate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
