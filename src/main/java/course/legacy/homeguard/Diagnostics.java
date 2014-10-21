package course.legacy.homeguard;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Diagnostics {
	public Map sensorTestStatusMap;
	public boolean runningSensorTest;
	public String sensorTestStatus;

	public void terminateSensorTest() {
		runningSensorTest = false;

		// look at individual sensor status to determine the overall test status
		sensorTestStatus = SensorTestStatus.PASS;
		for (Iterator iterator = sensorTestStatusMap.values().iterator(); iterator.hasNext();) {
			String status = (String) iterator.next();
			if (status.equals(SensorTestStatus.PENDING)) {
				sensorTestStatus = SensorTestStatus.FAIL;
				break;
			}
		}
	}

	public void update() {
		boolean done = true;
		for (Iterator iterator = sensorTestStatusMap.values().iterator(); iterator.hasNext();) {
			String testStatus = (String) iterator.next();
			if (SensorTestStatus.PENDING.equals(testStatus)) {
				done = false;
				break;
			}
		}

		// terminate test if complete
		if (done)
			terminateSensorTest();
	}

	public void run(List<Sensor> sensors) {
		runningSensorTest = true;
		sensorTestStatus = SensorTestStatus.PENDING;

		// initialize the status map
		sensorTestStatusMap = new HashMap();
		for (Iterator iterator = sensors.iterator(); iterator.hasNext();) {
			Sensor sensor = (Sensor) iterator.next();
			sensorTestStatusMap.put(sensor.getId(), SensorTestStatus.PENDING);
		}
	}

	public void update(Packet packet) {
		if (runningSensorTest) {
			if ("TRIPPED".equals(packet.getStatus())) {
				sensorTestStatusMap.put(packet.getId(), SensorTestStatus.PASS);
			}
			update();
		}
	}

	public Sensor findSensorById(List sensors, Packet packet) {
		// find sensor with id
		Sensor sensor = null;
		for (Iterator iterator = sensors.iterator(); iterator.hasNext();) {
			Sensor s = (Sensor) iterator.next();
			if (s.getId().equals(packet.getId())) {
				sensor = s;
				break;
			}
		}

		

		return sensor;
	}

}
