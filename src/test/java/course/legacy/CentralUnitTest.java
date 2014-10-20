package course.legacy;

import static org.junit.Assert.*;

import org.junit.Test;

import course.legacy.homeguard.CentralUnit;
import course.legacy.homeguard.Sensor;

public class CentralUnitTest {

	CentralUnit centralUnit = new CentralUnit();
	
	@Test
	public void showDefaultMessageWhencreateEmptySensors() {
		String message = centralUnit.getSensorMessage(new Sensor("", "", ""));
		assertEquals("default", message);
	}
	
	@Test
	public void doorIsClose() throws Exception {
		String message = centralUnit.getSensorMessage(new Sensor("0", "Door", Sensor.DOOR));
		assertEquals("Door is closed", message);
	}
	
	@Test
	public void windowsIsSealed() throws Exception {
		String message = centralUnit.getSensorMessage(new Sensor("0", "Window", Sensor.WINDOW));
		assertEquals("Window is sealed", message);
	}
	
	@Test
	public void motionIsMotionless() throws Exception {
		String message = centralUnit.getSensorMessage(new Sensor("0", "Motion", Sensor.MOTION));
		assertEquals("Motion is motionless", message);
	}
	
	@Test
	public void fireTemperatureIsNormal() throws Exception {
		String message = centralUnit.getSensorMessage(new Sensor("0", "FireBuilding", Sensor.FIRE));
		assertEquals("FireBuilding temperature is normal", message);
	}
	
	@Test
	public void doorIsOpenWhenSensorIsTripped() throws Exception {
		Sensor sensor = new Sensor("0", "Door", Sensor.DOOR);
		sensor.trip();
		String message = centralUnit.getSensorMessage(sensor);
		assertEquals("Door is open", message);
	}
	
	@Test
	public void windowIsAjarWhenSensorIsTripped() throws Exception {
		Sensor sensor = new Sensor("0", "Window", Sensor.WINDOW);
		sensor.trip();
		String message = centralUnit.getSensorMessage(sensor);
		assertEquals("Window is ajar", message);
	}
	
	@Test
	public void motionDetectedInBuildingWhenSensorIsTripped() throws Exception {
		Sensor sensor = new Sensor("0", "building", Sensor.MOTION);
		sensor.trip();
		String message = centralUnit.getSensorMessage(sensor);
		assertEquals("Motion detected in building", message);
	}
	
	@Test
	public void xxxIsOnFireWhenSensorIsTripped() throws Exception {
		Sensor sensor = new Sensor("0", "XXX", Sensor.FIRE);
		sensor.trip();
		String message = centralUnit.getSensorMessage(sensor);
		assertEquals("XXX is on FIRE!", message);
	}

}
