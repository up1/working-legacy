package course.legacy.homeguard;

public abstract class Sensor {
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

	public String getMessage() {
		if (!isTripped())
			return nonTrippedMessage();
		return trippedMessage();
	}

	public abstract String nonTrippedMessage();

	public abstract String trippedMessage();

	public void adjustStatus(String status) {
		if ("TRIPPED".equals(status))
			trip();
		else
			reset();
	}
}
