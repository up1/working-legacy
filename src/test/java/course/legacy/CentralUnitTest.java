package course.legacy;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import course.legacy.homeguard.AudibleAlarm;
import course.legacy.homeguard.CentralUnit;
import course.legacy.homeguard.DoorSensor;
import course.legacy.homeguard.HomeGuardView;
import course.legacy.homeguard.Sensor;

public class CentralUnitTest {

	@Test
	public void testNPEToRemoveIfNull() throws Exception {
		CentralUnit unit = new CentralUnit();
		unit.parseRadioBroadcast("1,TRIPPED");
	}

}
