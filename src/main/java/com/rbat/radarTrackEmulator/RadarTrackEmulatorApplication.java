package com.rbat.radarTrackEmulator;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RadarTrackEmulatorApplication  {

	public static void main(String[] args) {
		SpringApplication.run(RadarTrackEmulatorApplication.class, args);

		new RandomDataGenerationSimulator().start();

	}
}
