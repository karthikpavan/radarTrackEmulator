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



  @Autowired
  private RadartrackRepository radartrackRepository;

  @Autowired
  RadarTrackService radarTrackService;

//  @PostMapping("/saveData")
//  public Long saveData(@RequestBody Radartrack radartrack) {
//    radartrackRepository.save(radartrack);
//    return radartrack.getId();
//  }

  @PostMapping("/saveData")
  public ResponseEntity<?> saveData(@RequestBody Radartrack radartrack) {
    radarTrackService.saveData(radartrack);
    return new ResponseEntity("Radar added successfully", HttpStatus.OK);
  }


  @GetMapping("/getData")
  public List<Radartrack> getData() {
    return radartrackRepository.findAll();
  }

  @GetMapping(value = {"/"})
  public String getload() {
    return "Radar Tracker Emulator";
  }
}
