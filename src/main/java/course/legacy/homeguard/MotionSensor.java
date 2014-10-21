package course.legacy.homeguard;

public class MotionSensor extends Sensor {

	public MotionSensor(String id, String location, String type) {
		super(id, location, type);
	}

	public String getType() {
		return MOTION;
	}
	
	public String nonTrippedMessage() {
		return getLocation() + " is motionless";
	}

	public String trippedMessage() {
		return "Motion detected in " + getLocation();
	}

}
