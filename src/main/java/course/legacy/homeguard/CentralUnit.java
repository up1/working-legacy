package course.legacy.homeguard;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CentralUnit {

	private boolean armed = false;
	private String securityCode;
	List sensors = new LinkedList();
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
		Sensor sensor = findSensorWithId(id);
		sensor.checkTripOrReset(status);

		// get the message from the sensor and display it
		String message = sensor.getMessage();
		view.showMessage(message);

		// sound the alarm if armed
		if (isArmed())
			audibleAlarm.sound();

		// check if a sensor test is running and adjust status
		updateSensorTestStatus(id, status);
	}

	private Sensor findSensorWithId(String id) {
		for (Iterator iterator = sensors.iterator(); iterator.hasNext();) {
			Sensor s = (Sensor) iterator.next();
			if (s.getId().equals(id)) {
				return s;
			}
		}
		return null;
	}

	public void updateSensorTestStatus(String id, String status) {
		if (diagnostics.runningSensorTest) {
			if ("TRIPPED".equals(status)) {
				diagnostics.sensorTestStatusMap.put(id, SensorTestStatus.PASS);
			}
			updateWithCompleteStatus();
		}
	}

	private void updateWithCompleteStatus() {
		diagnostics.update();
	}

	public void runSensorTest() {
		diagnostics.run(sensors);
	}

	// used during sensor test
	public void terminateSensorTest() {
		diagnostics.terminateSensorTest();
	}

	// used during sensor test
	public String getSesnsorTestStatus() {
		return diagnostics.sensorTestStatus;
	}

	// used during sensor test
	public Map getSensorTestStatusMap() {
		return diagnostics.sensorTestStatusMap;
	}
}
