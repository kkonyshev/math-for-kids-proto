package mfk;

import mfk.service.ProfileService;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context-test.xml"})
public class ProfileFindTest {
	
	@Autowired
	@Qualifier(value="mockProfileService")
	private ProfileService profileService;
	
	@Test(expected=RuntimeException.class)
	public void testNotFound() {
		profileService.findByName("111");
	}
	
	@Test
	public void testFind() {
		ProfileImpl leoProfile = profileService.findByName(Utils.LEO_PROFILE_NAME);
		Utils.TESTS.debug("Profile info: " + leoProfile);
		Utils.TESTS.debug("Total  year(s) from birth day: " + leoProfile.getTotalYear());
		Utils.TESTS.debug("Total month(s) from birth day: " + leoProfile.getTotalMonth());
		profileService.listAll();
	}
	
	
	@Test
	public void testSize() {
		Assert.assertEquals("Ожидается один профиль", Integer.valueOf(1), profileService.size());
	}
}
