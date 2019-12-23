package com.example;

import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Putting the @SpringBootApplication annotation in the config project ensure that child projects
 * can find it and that the classpath scanning will occur for all the subpackages of this package.
 */
@SpringBootApplication
public class ApplicationConfiguration {}
