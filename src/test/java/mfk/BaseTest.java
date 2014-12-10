package mfk;

import mfk.util.Utils;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTest {
	private static final String LOG_TESTS = "LOG.TESTS";
	public static Logger TESTS = LoggerFactory.getLogger(LOG_TESTS);
	public static final String LEO_PROFILE_NAME = "Konyshev Leo";
	
	@Test
	public void testUtils() {
		Utils.randInteger(0, 1);
		Utils.randLong(0, 1);
	}
}
