package com.rbat.radarTrackEmulator;

import com.rbat.radarTrackEmulator.model.Radartrack;
import com.rbat.radarTrackEmulator.service.RadarTrackService;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class RandomDataGenerationSimulator {

  private Logger logger = LoggerFactory.getLogger(RandomDataGenerationSimulator.class);

  Random r;
  DecimalFormat coordinate;
  long id;
  DecimalFormat speeding;
  double latitude;
  double longitude;
  double speed;
  int heading;
  String area_type;

  Radartrack radartrack;

  @Autowired
  RadarTrackService radarTrackService;

  @Autowired
  MongoTemplate mongoTemplate;

  long delay = 5000;
  LoopTask task = new LoopTask();
  Timer timer = new Timer("TaskName");

  public void start() {
    logger.info(">> start function starts >>");
    timer.cancel();
    timer = new Timer("TaskName");
    Date executionDate = new Date(); // no params = now
    timer.scheduleAtFixedRate(task, executionDate, delay);
    logger.info("<< start function ends <<");
  }

  private class LoopTask extends TimerTask {

    public void run() {
      List<Radartrack> allData = radarTrackService.getData();

      radartrack = new Radartrack();
      for (int i = 1; i <= 3; i++) {
        List<Radartrack> dataList = new ArrayList<Radartrack>(i);
        switch (i) {
          case 1:
            logger.info("Type : Air");
            r = new Random();
            DecimalFormat coordinate = new DecimalFormat("#.####");
            coordinate.setRoundingMode(RoundingMode.CEILING);

            speeding = new DecimalFormat("#.##");
            speeding.setRoundingMode(RoundingMode.CEILING);

            id = Long.valueOf(r.nextInt(900) + 100);
            radartrack.setId(id);

            latitude = r.nextDouble();
            radartrack.setLatitude(Double.parseDouble(coordinate.format(latitude)));

            longitude = r.nextDouble();
            radartrack.setLongitude(Double.parseDouble(coordinate.format(longitude)));

            speed = r.nextDouble();
            radartrack.setSpeed(Double.parseDouble(speeding.format(speed)));

            heading = r.nextInt(360);
            radartrack.setHeading(heading);

            radartrack.setArea_types("Air");
            dataList.add(radartrack);
            break;

          case 2:
            logger.info("Type : Sea");
            coordinate = new DecimalFormat("#.####");
            coordinate.setRoundingMode(RoundingMode.CEILING);

            speeding = new DecimalFormat("#.##");
            speeding.setRoundingMode(RoundingMode.CEILING);

            id = Long.valueOf(r.nextInt(900) + 100);
            radartrack.setId(id);

            latitude = r.nextDouble();
            radartrack.setLatitude(Double.parseDouble(coordinate.format(latitude)));

            longitude = r.nextDouble();
            radartrack.setLongitude(Double.parseDouble(coordinate.format(longitude)));

            speed = r.nextDouble();
            radartrack.setSpeed(Double.parseDouble(speeding.format(speed)));

            heading = r.nextInt(360);
            radartrack.setHeading(heading);

            radartrack.setArea_types("Sea");
            dataList.add(radartrack);
            break;

          case 3:
            logger.info("Type : Ground");
            Random r = new Random();
            coordinate = new DecimalFormat("#.####");
            coordinate.setRoundingMode(RoundingMode.CEILING);

            speeding = new DecimalFormat("#.##");
            speeding.setRoundingMode(RoundingMode.CEILING);

            id = Long.valueOf(r.nextInt(900) + 100);
            radartrack.setId(id);

            latitude = r.nextDouble();
            radartrack.setLatitude(Double.parseDouble(coordinate.format(latitude)));

            longitude = r.nextDouble();
            radartrack.setLongitude(Double.parseDouble(coordinate.format(longitude)));

            speed = r.nextDouble();
            radartrack.setSpeed(Double.parseDouble(speeding.format(speed)));

            heading = r.nextInt(360);
            radartrack.setHeading(heading);

            radartrack.setArea_types("Ground");
            dataList.add(radartrack);
            break;

          default:
            logger.info("No data");
            break;
        }

        for (int j = 0; j < dataList.toArray().length; j++) {
          logger.info("-------Start-------");
          logger.info(dataList.get(j).getId() + ","
              + dataList.get(j).getLatitude() + ","
              + dataList.get(j).getLongitude() + ","
              + dataList.get(j).getHeading() + ","
              + dataList.get(j).getSpeed() + ","
              + dataList.get(j).getArea_types());

          for (Radartrack dataId : allData) {
            logger.info("ID : " + dataId.getId());
            if (dataList.get(j).getId() != dataId.getId()) {
              mongoTemplate.remove(Query.query(Criteria.where("id").is(dataId.getId())), Radartrack.class);
            }
          }
          radarTrackService.saveData(dataList.get(j));
          logger.info("-------end-------");
        }
      }



    }
  }
}
