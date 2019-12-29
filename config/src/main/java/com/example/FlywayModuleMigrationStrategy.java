package com.example;

import java.util.List;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.stereotype.Component;

@Component
public class FlywayModuleMigrationStrategy implements FlywayMigrationStrategy {

  private final List<Module> modules;

  @Autowired
  public FlywayModuleMigrationStrategy(List<Module> modules) {
    this.modules = modules;
  }

  @Override
  public void migrate(Flyway flyway) {
    for (var module : modules) {
      module.migrateSchema();
    }
  }
}
