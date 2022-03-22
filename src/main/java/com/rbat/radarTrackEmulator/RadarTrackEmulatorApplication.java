package com.rbat.radarTrackEmulator;

import com.rbat.radarTrackEmulator.model.Radartrack;
import com.rbat.radarTrackEmulator.repository.RadartrackRepository;
import com.rbat.radarTrackEmulator.service.RadarTrackService;
import com.rbat.radarTrackEmulator.service.RadarTrackStudentServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component
public class RadarTrackEmulatorApplication  {

	Random r;
	DecimalFormat coordinate;
	long id;
	DecimalFormat speeding;
	double latitude;
	double longitude;
	double speed;
	int heading;
	String area_type;

	List<Radartrack> dataList;

	@Autowired
	RadarTrackService radarTrackService;


	public static void main(String[] args) {
		SpringApplication.run(RadarTrackEmulatorApplication.class, args);

		new RadarTrackEmulatorApplication().start();
	}

	long delay = 9000; // delay in milliseconds
	LoopTask task = new LoopTask();
	Timer timer = new Timer("TaskName");

	public void start() {
		System.out.println(">> start function starts >>");
		timer.cancel();
		timer = new Timer("TaskName");
		Date executionDate = new Date(); // no params = now
		timer.scheduleAtFixedRate(task, executionDate, delay);
		System.out.println("<< start function ends <<");
	}

	private class LoopTask extends TimerTask {
		public void run() {

//			List<Radartrack> dataList = new ArrayList<>();

			 dataList = new ArrayList<>();

			Radartrack radartrack = new Radartrack();
			for(int i=1;i<=3;i++) {
				switch (i) {
					case 1:
						System.out.println("***************************Start***************************");

						r = new Random();
						DecimalFormat coordinate = new DecimalFormat("#.####");
						coordinate.setRoundingMode(RoundingMode.CEILING);

						speeding = new DecimalFormat("#.##");
						speeding.setRoundingMode(RoundingMode.CEILING);

						id = 	Long.valueOf(r.nextInt(900) + 100);
						radartrack.setId(id);
						System.out.println("ID :"+id);
						System.out.println("----------------------");

						latitude = r.nextDouble();
						radartrack.setLatitude(Double.parseDouble(coordinate.format(latitude)));
						System.out.println("Latitude :"+coordinate.format(latitude));
						System.out.println("----------------------");

						longitude = r.nextDouble();
						radartrack.setLongitude(Double.parseDouble(coordinate.format(longitude)));
						System.out.println("Longitude :"+coordinate.format(longitude));
						System.out.println("----------------------");

						speed = r.nextDouble();
						radartrack.setSpeed(Double.parseDouble(speeding.format(speed)));
						System.out.println("Speed :"+speeding.format(speed));
						System.out.println("----------------------");

						heading = r.nextInt(360);
						radartrack.setHeading(heading);
						System.out.println("Heading Direction:"+heading);
						System.out.println("----------------------");

						radartrack.setArea_types("Air");
						System.out.println("Type :"+"Air");

						dataList.add(radartrack);
						System.out.println("***************************End***************************");
						break;

					case 2:
						System.out.println("***************************Start***************************");

						coordinate = new DecimalFormat("#.####");
						coordinate.setRoundingMode(RoundingMode.CEILING);

						speeding = new DecimalFormat("#.##");
						speeding.setRoundingMode(RoundingMode.CEILING);

						id = 	Long.valueOf(r.nextInt(900) + 100);
						radartrack.setId(id);
						System.out.println("ID :"+id);
						System.out.println("----------------------");

						latitude = r.nextDouble();
						radartrack.setLatitude(Double.parseDouble(coordinate.format(latitude)));
						System.out.println("Latitude :"+coordinate.format(latitude));
						System.out.println("----------------------");

						longitude = r.nextDouble();
						radartrack.setLongitude(Double.parseDouble(coordinate.format(longitude)));
						System.out.println("Longitude :"+coordinate.format(longitude));
						System.out.println("----------------------");

						speed = r.nextDouble();
						radartrack.setSpeed(Double.parseDouble(speeding.format(speed)));
						System.out.println("Speed :"+speeding.format(speed));
						System.out.println("----------------------");

						heading = r.nextInt(360);
						radartrack.setHeading(heading);
						System.out.println("Heading Direction:"+heading);
						System.out.println("----------------------");

						radartrack.setArea_types("Sea");
						System.out.println("Type :"+"Sea");

						dataList.add(radartrack);
						System.out.println("***************************End***************************");
						break;

					case 3:
						System.out.println("***************************Start***************************");

						Random r = new Random();
						coordinate = new DecimalFormat("#.####");
						coordinate.setRoundingMode(RoundingMode.CEILING);

						speeding = new DecimalFormat("#.##");
						speeding.setRoundingMode(RoundingMode.CEILING);

						id = 	Long.valueOf(r.nextInt(900) + 100);
						radartrack.setId(id);
						System.out.println("ID :"+id);
						System.out.println("----------------------");

						latitude = r.nextDouble();
						radartrack.setLatitude(Double.parseDouble(coordinate.format(latitude)));
						System.out.println("Latitude :"+coordinate.format(latitude));
						System.out.println("----------------------");

						longitude = r.nextDouble();
						radartrack.setLongitude(Double.parseDouble(coordinate.format(longitude)));
						System.out.println("Longitude :"+coordinate.format(longitude));
						System.out.println("----------------------");

						speed = r.nextDouble();
						radartrack.setSpeed(Double.parseDouble(speeding.format(speed)));
						System.out.println("Speed :"+speeding.format(speed));
						System.out.println("----------------------");

						heading = r.nextInt(360);
						radartrack.setHeading(heading);
						System.out.println("Heading Direction:"+heading);
						System.out.println("----------------------");

						radartrack.setArea_types("Ground");
						System.out.println("Type :"+"Ground");

						dataList.add(radartrack);
						System.out.println("***************************End***************************");
						break;

					default: System.out.println("No data");
						break;
				}
			}
			 System.out.println("Data List size : "+dataList.size());

			for (Radartrack data : dataList) {
				System.out.println(data.getId()+","
						+data.getLatitude()+","
						+data.getLongitude()+","
						+data.getHeading()+","
						+data.getSpeed()+","
						+data.getArea_types());

				radarTrackService.saveData(data);
			}
		}
	}

}
