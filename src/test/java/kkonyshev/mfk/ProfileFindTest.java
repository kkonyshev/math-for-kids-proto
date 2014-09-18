package kkonyshev.mfk;

import kkonyshev.mfk.service.ProfileService;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context-test.xml"})
public class ProfileFindTest {


	
	@Autowired
	private ProfileService profileService;
	
	@Test(expected=RuntimeException.class)
	public void testNotFound() {
		profileService.findByName("111");
	}
	
	@Test
	public void testFind() {
		Profile leoProfile = profileService.findByName("Konyshev Leo");
		LoggingUtils.TESTS.debug("Profile info: " + leoProfile);
		LoggingUtils.TESTS.debug("Total  year(s) from birth day: " + leoProfile.getTotalYear());
		LoggingUtils.TESTS.debug("Total month(s) from birth day: " + leoProfile.getTotalMonth());
	}
	
	
	@Test
	public void testCheckTrainings() {
		Profile leoProfile = profileService.findByName("Konyshev Leo");
		Assert.assertEquals("Ожидается две тренировки", Integer.valueOf(2), Integer.valueOf(leoProfile.getTrainingList().size()));
	}
	@Test
	public void testSize() {
		Assert.assertEquals("Ожидается один профиль", Integer.valueOf(1), profileService.size());
	}
}
