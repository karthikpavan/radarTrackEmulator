package com.rbat.radarTrackEmulator.repository;

import com.rbat.radarTrackEmulator.model.Radartrack;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface RadartrackRepository extends MongoRepository<Radartrack, String> {



}
