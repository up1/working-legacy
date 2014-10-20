package course.legacy.homeguard;

public class Sensor {
	public static final String DOOR = "door";
	public static final String MOTION = "motion";
	public static final String WINDOW = "window";
	public static final String FIRE = "fire";

	private String id;
	private String location;
	private String type;
	private boolean tripped = false;

	public Sensor(String id, String location, String type) {
		this.id = id;
		this.location = location;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public String getLocation() {
		return location;
	}

	public void trip() {
		tripped = true;
	}

	public void reset() {
		tripped = false;
	}

	public boolean isTripped() {
		return tripped;
	}
	
	public void setTripped(boolean tripped) {
		this.tripped = tripped;
	}

	public void checkTripOrReset(String status) {
		if ("TRIPPED".equals(status))
			trip();
		else
			reset();
	}

	public String getMessage() {
		String message = "default";
		if (!isTripped()) {
			if (getType().equals(Sensor.DOOR))
				return getLocation() + " is closed";
			else if (getType().equals(Sensor.WINDOW))
				return getLocation() + " is sealed";
			else if (getType().equals(Sensor.MOTION))
				return getLocation() + " is motionless";
			else if (getType().equals(Sensor.FIRE))
				return getLocation() + " temperature is normal";
		} else {
			if (getType().equals(Sensor.DOOR))
				return getLocation() + " is open";
			else if (getType().equals(Sensor.WINDOW))
				return getLocation() + " is ajar";
			else if (getType().equals(Sensor.MOTION))
				return "Motion detected in " + getLocation();
			else if (getType().equals(Sensor.FIRE))
				return getLocation() + " is on FIRE!";
		}
		return message;
	}
}
