package mfk;

import mfk.domain.Profile;
import mfk.domain.ProfileService;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(locations = {"classpath:application-context-test.xml"})
public class ProfileFindTest {
	
	@Autowired
	@Qualifier(value="mockProfileService")
	private ProfileService profileService;
	
	@Test(expected=RuntimeException.class)
	public void testNotFound() {
		profileService.findProfileByName("111");
	}
	
	@Test
	public void testFind() {
		Profile leoProfile = (Profile) profileService.findProfileByName(BaseTest.LEO_PROFILE_NAME);
		BaseTest.TESTS.debug("Profile info: " + leoProfile);
		BaseTest.TESTS.debug("Birth Date: " + leoProfile.getBirthDate());
		BaseTest.TESTS.debug("Total  year(s) from birth day: " + leoProfile.getTotalYear());
		BaseTest.TESTS.debug("Total month(s) from birth day: " + leoProfile.getTotalMonth());
		profileService.getProfileList();
	}
	
	@Test
	public void testSize() {
		Assert.assertEquals("Ожидается один профиль", Integer.valueOf(1), profileService.profileCount());
	}
}
