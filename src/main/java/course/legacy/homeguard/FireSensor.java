package course.legacy.homeguard;

public class FireSensor extends Sensor {

	public FireSensor(String id, String location, String type) {
		super(id, location, type);
	}

	public String getType() {
		return FIRE;
	}
	
	public String nonTrippedMessage() {
		return getLocation() + " temperature is normal";
	}

	public String trippedMessage() {
		return getLocation() + " is on FIRE!";
	}

}
