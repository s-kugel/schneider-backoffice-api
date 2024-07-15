package com.s_kugel.schneider.backoffice;

import io.micronaut.context.ApplicationContextBuilder;
import io.micronaut.context.ApplicationContextConfigurer;
import io.micronaut.context.annotation.ContextConfigurer;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.runtime.Micronaut;

public class Application {

  @ContextConfigurer
  public static class DefaultEnvironmentConfigure implements ApplicationContextConfigurer {

    @Override
    public void configure(@NonNull ApplicationContextBuilder builder) {
      builder.defaultEnvironments("default", "eule-db", "fasan-db");
    }
  }

  public static void main(String[] args) {
    Micronaut.run(Application.class, args);
  }
}
