package mfk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mfk.domain.NumberStat;
import mfk.util.Utils;

import org.junit.Assert;
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
	
	@Test
	public void testNumberStatHash() {
		NumberStat s1 = new NumberStat();
		s1.setProfileId(1L);
		s1.setNumber(1);
		s1.setCount(1);
		
		NumberStat s2 = new NumberStat();
		s2.setProfileId(1L);
		s2.setNumber(1);
		s2.setCount(2);
		
		Set<NumberStat> set = new HashSet<NumberStat>();
		set.add(s1);
		set.add(s2);
		
		Assert.assertEquals("Дублирующихся записей быть не должно", 1, set.size());
		
		List<NumberStat> list = new ArrayList<NumberStat>();
		list.add(s1);
		list.add(s2);
		Collections.sort(list);
		TESTS.debug(list.toString());
		
		Assert.assertEquals("Ожидается две записи в списке", 2, list.size());
	}
}
