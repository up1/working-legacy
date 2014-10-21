package course.legacy.homeguard;

public class WindowSensor extends Sensor {

	public WindowSensor(String id, String location, String type) {
		super(id, location, type);
	}

	public String getType() {
		return WINDOW;
	}
	
	public String nonTrippedMessage() {
		return getLocation() + " is sealed";
	}

	public String trippedMessage() {
		return getLocation() + " is ajar";
	}

}
