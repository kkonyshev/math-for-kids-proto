package kkonyshev.mfk;

import kkonyshev.mfk.service.ProfileService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context-test.xml"})
public class ProfileSaveTest {

	@Autowired
	private ProfileService profileService;
	
	@Test
	public void testSave() {
		Profile profile = profileService.findByName("Konyshev Leo");
		String newName = "Leo Konyshev";
		profile.setName(newName);
		profileService.save(profile);
		profileService.findByName(newName);
	}
}
