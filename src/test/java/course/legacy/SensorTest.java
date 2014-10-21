package course.legacy;

import static org.junit.Assert.*;

import org.junit.Test;

import course.legacy.homeguard.CentralUnit;
import course.legacy.homeguard.DoorSensor;
import course.legacy.homeguard.Sensor;

public class SensorTest {
	
	private static Sensor makeSensor(String id, String location, String type) {
		if(Sensor.DOOR.equals(type)) {
			return new DoorSensor(id, location, type);
		}
		return new Sensor(id, location, type);
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
