package com.rbat.radarTrackEmulator.service;

import com.rbat.radarTrackEmulator.model.Radartrack;
import java.util.List;

public interface RadarTrackService {

  Radartrack saveData(Radartrack radartrack);

  public List<Radartrack> getData();

}
