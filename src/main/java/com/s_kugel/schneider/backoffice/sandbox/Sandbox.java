package com.s_kugel.schneider.backoffice.sandbox;

import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Sandbox {

  public void execute(LocalDate basisDate) {
    // overwrite arguments
    basisDate = LocalDate.of(2024, 4, 1);

    log.info("basisDate: {}", basisDate);
  }
}
