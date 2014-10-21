package server;

import static org.junit.Assert.*;

import org.junit.Test;

public class ServerTest {

	@Test
	public void testX() throws Exception {
		Server server = new Server();
		Config config = server.buildConfigFromArgs(new String[]{"","", "", "", "", "1"});
		assertFalse(config.debugOn);
	}

}
