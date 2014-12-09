package mfk;

import mfk.api.Profile;
import mfk.api.ProfileService;

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
		Profile profile = profileService.findByName(Utils.LEO_PROFILE_NAME);
		profileService.remove(profile);
		profileService.findByName(Utils.LEO_PROFILE_NAME);
	}
}
