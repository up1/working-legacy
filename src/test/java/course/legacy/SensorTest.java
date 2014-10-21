package course.legacy;

import static org.junit.Assert.*;

import org.junit.Test;

import course.legacy.homeguard.CentralUnit;
import course.legacy.homeguard.DoorSensor;
import course.legacy.homeguard.FireSensor;
import course.legacy.homeguard.MotionSensor;
import course.legacy.homeguard.Sensor;
import course.legacy.homeguard.WindowSensor;

public class SensorTest {

	private static Sensor makeSensor(String id, String location, String type) {
		if (Sensor.DOOR.equals(type)) {
			return new DoorSensor(id, location, type);
		} else if (Sensor.WINDOW.equals(type)) {
			return new WindowSensor(id, location, type);
		} else if (Sensor.MOTION.equals(type)) {
			return new MotionSensor(id, location, type);
		} else if (Sensor.FIRE.equals(type)) {
			return new FireSensor(id, location, type);
		}
		throw new IllegalArgumentException();
	}

	public static void assertSensorMessages(String type, String nonTrippedMessage, String trippedMessage) {
		{
			Sensor sensor = makeSensor("0", "<x>", type);
			assertEquals(nonTrippedMessage, sensor.getMessage());
		}
		{
			Sensor sensor = makeSensor("0", "<x>", type);
			sensor.trip();
			assertEquals(trippedMessage, sensor.getMessage());
		}
	}

	@Test
	public void sensorMessage() throws Exception {
		assertSensorMessages(Sensor.DOOR, "<x> is closed", "<x> is open");
		assertSensorMessages(Sensor.WINDOW, "<x> is sealed", "<x> is ajar");
		assertSensorMessages(Sensor.MOTION, "<x> is motionless", "Motion detected in <x>");
		assertSensorMessages(Sensor.FIRE, "<x> temperature is normal", "<x> is on FIRE!");
	}

}
