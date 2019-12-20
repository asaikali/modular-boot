package com.example;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

/**
 * We need this class so that spring boot knows to use package this class is in to scan for
 * components. Any spring components in the com.example subpackages will be scanned and auto
 * configured by boot.
 *
 * <p>The app module contains the @SpringBootApplication annotation which turns on autoconfiguration
 * for all subpackages. Due to this project's organization we need we need this class to make sure
 * that boot knows what packages to autoconfigure.
 */
@EnableAutoConfiguration
public class ModuleConfiguration {}
