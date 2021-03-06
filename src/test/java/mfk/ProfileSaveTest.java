package mfk;

import mfk.domain.Profile;
import mfk.domain.ProfileService;

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
public class ProfileSaveTest {

	@Autowired
	@Qualifier(value="mockProfileService")
	private ProfileService profileService;
	
	@Test
	public void testSave() {
		Profile profile = profileService.findProfileByName(BaseTest.LEO_PROFILE_NAME);
		String newName = "Leo Konyshev";
		profile.setName(newName);
		profileService.save(profile);
		profileService.findProfileByName(newName);
	}
}
