package kkonyshev.mfk;

import kkonyshev.mfk.service.ProfileService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context-test.xml"})
public class ProfileRemoveTest {

	@Autowired
	private ProfileService profileService;
	
	@Test(expected=RuntimeException.class)
	public void testRemove() {
		Profile profile = profileService.findByName("Konyshev Leo");
		profileService.remove(profile);
		profileService.findByName("Konyshev Leo");
	}
}
