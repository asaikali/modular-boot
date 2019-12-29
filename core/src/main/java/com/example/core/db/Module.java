package com.example.core.db;

import java.util.Objects;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A module is a collection of related spring components that use a private database schema. The
 * module's database schema is managed by flyway. Modules are defined by subclassing this class and
 * setting the schema name that the module is supposed to use. A single instance of each subclass
 * must be created as Spring Bean so that {@link FlywayModuleMigrationStrategy} can find the module
 * and call it's migrate method.
 */
public abstract class Module {
  private final Logger logger = LoggerFactory.getLogger(Module.class);
  private final String schemaName;
  private final Flyway flyway;

  /**
   * Configure a module. If the schemaName is set 'email' then a database schema called email will
   * be created and the migration scripts are expected to be in db/mail location.
   *
   * @param dataSource the datasource to use to create the module's database schema
   * @param schemaName the name of the schema that the module should use.
   */
  protected Module(DataSource dataSource, String schemaName) {
    this.schemaName = schemaName;
    this.flyway =
        Flyway.configure()
            .schemas(schemaName)
            .locations("db/" + schemaName)
            .dataSource(Objects.requireNonNull(dataSource))
            .load();
  }

  /**
   * Apply flyway migration for this module to ensure that the database tables that the module
   * requires are in place.
   */
  public void migrateSchema() {
    flyway.migrate();
    logger.info("Migrated schema for module '{}'", schemaName);
  }

  /**
   * Rebuilds the modules schema by first dropping the schema then applying the database migrations
   * associated with this module. This method is designed for use by integration tests to restore
   * the state of the database to a well known state. For example
   *
   * <pre>{@code
   * @BeforeAll
   * public static void beforeAll(@Autowired EmailModule emailModule) {
   *   emailModule.rebuildSchema();
   * }
   * }</pre>
   */
  public void rebuildSchema() {
    flyway.clean();
    flyway.migrate();
    logger.info("Rebuilt schema for module '{}'", schemaName);
  }

  /**
   * Get the name name of the database schema for this module.
   *
   * @return the name module name database schema
   */
  public String getSchemaName() {
    return schemaName;
  }
}
