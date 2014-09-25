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
	
	@Test
	public void testProgress() {
		Profile leoProfile = profileService.findByName("Konyshev Leo");
		
		for (AbstractTraining trainting: leoProfile.getTrainingList()) {
			checkProgressIsEmpty(trainting);
			System.out.println(trainting.getName() + " progress: " + trainting.getProgressPercentage() + "%");
		}
		
		for (AbstractTraining trainting: leoProfile.getTrainingList()) {
			if (trainting instanceof TrainingAmounts) {
				trainting.updateProgressItem(Integer.valueOf(0), ActionType.View);
				trainting.updateProgressItem(Integer.valueOf(1), ActionType.View);
				trainting.updateProgressItem(Integer.valueOf(3), ActionType.View);
				trainting.updateProgressItem(Integer.valueOf(4), ActionType.View);
				trainting.updateProgressItem(Integer.valueOf(0), ActionType.View);
				LoggingUtils.TESTS.debug(trainting.printProgressStat());
			}
		}
		
		for (AbstractTraining trainting: leoProfile.getTrainingList()) {
			if (trainting instanceof TrainingAmounts) {
				Assert.assertEquals(2, trainting.getProgressFor(0).size());
				Assert.assertEquals(1, trainting.getProgressFor(1).size());
				Assert.assertTrue(trainting.getProgressFor(2).isEmpty());
				Assert.assertEquals(1, trainting.getProgressFor(3).size());
				Assert.assertEquals(1, trainting.getProgressFor(4).size());
				Assert.assertTrue(trainting.getProgressFor(5).isEmpty());
			} else {
				checkProgressIsEmpty(trainting);
			}
			System.out.println(trainting.getName() + " progress: " + trainting.getProgressPercentage() + "%");
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testProgressIllegalAgrumentNegative() {
		Profile leoProfile = profileService.findByName("Konyshev Leo");		
		for (AbstractTraining trainting: leoProfile.getTrainingList()) {
			trainting.updateProgressItem(Integer.valueOf(-1), ActionType.View);
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testProgressIllegalAgrumentMoreThenMax() {
		Profile leoProfile = profileService.findByName("Konyshev Leo");		
		for (AbstractTraining trainting: leoProfile.getTrainingList()) {
			trainting.updateProgressItem(Integer.valueOf(101), ActionType.View);
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testProgressIllegalAgrumentNull() {
		Profile leoProfile = profileService.findByName("Konyshev Leo");		
		for (AbstractTraining trainting: leoProfile.getTrainingList()) {
			trainting.updateProgressItem(null, ActionType.View);
		}
	}

	private void checkProgressIsEmpty(AbstractTraining trainting) {
		Assert.assertTrue(trainting.getProgressFor(0).isEmpty());
		Assert.assertTrue(trainting.getProgressFor(1).isEmpty());
		Assert.assertTrue(trainting.getProgressFor(2).isEmpty());
		Assert.assertTrue(trainting.getProgressFor(3).isEmpty());
		Assert.assertTrue(trainting.getProgressFor(4).isEmpty());
		Assert.assertTrue(trainting.getProgressFor(5).isEmpty());
	}
}
