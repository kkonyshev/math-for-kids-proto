package mfk;

import mfk.domain.Profile;
import mfk.domain.ProfileService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context-test.xml"})
public class ProfileRemoveTest {

	@Autowired
	@Qualifier(value="mockProfileService")
	private ProfileService profileService;
	
	@Test(expected=RuntimeException.class)
	public void testRemove() {
		Profile profile = profileService.findProfileByName(BaseTest.LEO_PROFILE_NAME);
		profileService.remove(profile);
		profileService.findProfileByName(BaseTest.LEO_PROFILE_NAME);
	}
}
