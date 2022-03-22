package com.rbat.radarTrackEmulator.service;

import com.rbat.radarTrackEmulator.model.Radartrack;
import com.rbat.radarTrackEmulator.repository.RadartrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RadarTrackStudentServiceImpl implements RadarTrackService {

  @Autowired
  RadartrackRepository radartrackRepository;

  @Override
  public Radartrack saveData(Radartrack radartrack) {
    return radartrackRepository.save(radartrack);
  }
}
