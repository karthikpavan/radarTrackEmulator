package com.rbat.radarTrackEmulator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RadarTrackEmulatorApplication implements CommandLineRunner {

  @Autowired
  RandomDataGenerationSimulator randomDataGenerationSimulator;

  private Logger logger = LoggerFactory.getLogger(RadarTrackEmulatorApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(RadarTrackEmulatorApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    logger.info("run function starts >>");

    randomDataGenerationSimulator.start();

  }
}
