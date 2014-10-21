package course.legacy.homeguard;

public class DoorSensor extends Sensor {
	
	public DoorSensor(String id, String location, String type) {
		super(id, location, type);
	}

	public String getType() {
		return DOOR;
	}

	public String nonTrippedMessage() {
		return getLocation() + " is closed";
	}

	public String trippedMessage() {
		return getLocation() + " is open";
	}

}
