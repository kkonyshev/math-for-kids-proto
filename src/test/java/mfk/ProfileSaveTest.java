package mfk;

import mfk.domain.IProfile;
import mfk.domain.ProfileService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context-test.xml"})
public class ProfileSaveTest {

	@Autowired
	@Qualifier(value="mockProfileService")
	private ProfileService profileService;
	
	@Test
	public void testSave() {
		IProfile profile = profileService.findProfileByName(BaseTest.LEO_PROFILE_NAME);
		String newName = "Leo Konyshev";
		profile.setName(newName);
		profileService.save(profile);
		profileService.findProfileByName(newName);
	}
}
