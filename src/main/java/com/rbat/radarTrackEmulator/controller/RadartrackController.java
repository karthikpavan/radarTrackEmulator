package com.rbat.radarTrackEmulator.controller;

import com.rbat.radarTrackEmulator.model.Radartrack;
import com.rbat.radarTrackEmulator.repository.RadartrackRepository;
import com.rbat.radarTrackEmulator.service.RadarTrackService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class RadartrackController {

  private Logger logger = LoggerFactory.getLogger(RadartrackController.class);

  @Autowired
  RadarTrackService radarTrackService;

  @PostMapping("/saveData")
  public ResponseEntity<?> saveData(@RequestBody Radartrack radartrack) {
    logger.debug("Radar Data received : "
        + radartrack.getId() + ","
        + radartrack.getLatitude() + ","
        + radartrack.getLatitude() + ","
        + radartrack.getSpeed() + ","
        + radartrack.getHeading() + ","
        + radartrack.getArea_types());
    radarTrackService.saveData(radartrack);
    return new ResponseEntity("Radar data added successfully ", HttpStatus.OK);
  }

  @GetMapping("/getData")
  public List<Radartrack> getData() {
    logger.debug(">> getting all available data from DB >>");
    return radarTrackService.getData();
  }

  @GetMapping(value = {"/"})
  public String getload() {
    return "Radar Tracker Emulator";
  }
}
