package course.legacy.homeguard;

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
	
	public void addSensor(Sensor sensor) {
		sensors.add(sensor);
	}

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
		Packet newPacket = new Packet(packet);

		Sensor sensor = diagnostics.findSensorById(sensors, newPacket);

		// get the message from the sensor and display it
		String message = sensor.getMessage();
		view.showMessage(message);

		// sound the alarm if armed
		if (isArmed())
			audibleAlarm.sound();

		diagnostics.update(newPacket);
	}

	public void updateWithCompleteStatus() {
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
