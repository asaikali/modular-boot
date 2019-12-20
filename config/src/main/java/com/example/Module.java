package com.example;

/**
 * An interface representing a module that has it's own schcema that is managed by flyway. Each
 * module should implement this interface and annotate it with the spring
 * org.springframework.stereotype.Component annotation
 */
public interface Module {
  public abstract String getName();
}
