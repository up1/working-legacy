package course.legacy.homeguard;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CentralUnit {

	private boolean armed = false;
	private String securityCode;
	private List sensors = new LinkedList();
	private HomeGuardView view = new TextView();
	private AudibleAlarm audibleAlarm = new TextAudibleAlarm();

	Diagnostics diagnostics = new Diagnostics();

	public boolean isArmed() {
		return armed;
	}

	public void arm() {
		armed = true;
	}

	public void setSecurityCode(String code) {
		securityCode = code;
	}

	public boolean isValidCode(String code) {
		return securityCode.equals(code);
	}

	public void enterCode(String code) {
		if (isValidCode(code)) {
			armed = false;
			audibleAlarm.silence();
		}
	}

	public boolean audibleAlarmIsOn() {
		return false;
	}

	public List getSensors() {
		return sensors;
	}

	public void registerSensor(Sensor sensor) {
		sensors.add(sensor);
	}

	public void setView(HomeGuardView view) {
		this.view = view;
	}

	public void setAudibleAlarm(AudibleAlarm alarm) {
		audibleAlarm = alarm;
	}

	public void parseRadioBroadcast(String packet) {
		// parse the packet
		String[] tokens = packet.split(",");
		String id = tokens[0];
		String status = tokens[1];

		// find sensor with id
		Sensor sensor = null;
		for (Iterator iterator = sensors.iterator(); iterator.hasNext();) {
			Sensor s = (Sensor) iterator.next();
			if (s.getId().equals(id)) {
				sensor = s;
				break;
			}
		}

		// trip or reset sensor
		if (sensor != null) {
			if ("TRIPPED".equals(status))
				sensor.trip();
			else
				sensor.reset();
		}

		// get the message from the sensor and display it
		String message = getSensorMessage(sensor);
		view.showMessage(message);

		// sound the alarm if armed
		if (isArmed())
			audibleAlarm.sound();

		// check if a sensor test is running and adjust status
		updateSensorTestStatus(id, status);
	}

	private void updateSensorTestStatus(String id, String status) {
		if (diagnostics.runningSensorTest) {
			if ("TRIPPED".equals(status)) {
				diagnostics.sensorTestStatusMap.put(id, SensorTestStatus.PASS);
			}

			// check to see if test is complete
			boolean done = true;
			for (Iterator iterator = diagnostics.sensorTestStatusMap.values().iterator(); iterator.hasNext();) {
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
	}

	public void runSensorTest() {
		diagnostics.runningSensorTest = true;
		diagnostics.sensorTestStatus = SensorTestStatus.PENDING;

		// initialize the status map
		diagnostics.sensorTestStatusMap = new HashMap();
		for (Iterator iterator = sensors.iterator(); iterator.hasNext();) {
			Sensor sensor = (Sensor) iterator.next();
			diagnostics.sensorTestStatusMap.put(sensor.getId(), SensorTestStatus.PENDING);
		}
	}

	// used during sensor test
	public void terminateSensorTest() {
		diagnostics.runningSensorTest = false;

		// look at individual sensor status to determine the overall test status
		diagnostics.sensorTestStatus = SensorTestStatus.PASS;
		for (Iterator iterator = diagnostics.sensorTestStatusMap.values().iterator(); iterator.hasNext();) {
			String status = (String) iterator.next();
			if (status.equals(SensorTestStatus.PENDING)) {
				diagnostics.sensorTestStatus = SensorTestStatus.FAIL;
				break;
			}
		}
	}

	// used during sensor test
	public String getSesnsorTestStatus() {
		return diagnostics.sensorTestStatus;
	}

	// used during sensor test
	public Map getSensorTestStatusMap() {
		return diagnostics.sensorTestStatusMap;
	}

	public String getSensorMessage(Sensor sensor) {
		String message = "default";
		if (!sensor.isTripped()) {
			if (sensor.getType().equals(Sensor.DOOR))
				return sensor.getLocation() + " is closed";
			else if (sensor.getType().equals(Sensor.WINDOW))
				return sensor.getLocation() + " is sealed";
			else if (sensor.getType().equals(Sensor.MOTION))
				return sensor.getLocation() + " is motionless";
			else if (sensor.getType().equals(Sensor.FIRE))
				return sensor.getLocation() + " temperature is normal";
		} else {
			if (sensor.getType().equals(Sensor.DOOR))
				return sensor.getLocation() + " is open";
			else if (sensor.getType().equals(Sensor.WINDOW))
				return sensor.getLocation() + " is ajar";
			else if (sensor.getType().equals(Sensor.MOTION))
				return "Motion detected in " + sensor.getLocation();
			else if (sensor.getType().equals(Sensor.FIRE))
				return sensor.getLocation() + " is on FIRE!";
		}
		return message;
	}
}
