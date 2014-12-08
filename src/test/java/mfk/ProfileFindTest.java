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
	public void testCheckTrainings() {
		ProfileImpl leoProfile = profileService.findByName(Utils.LEO_PROFILE_NAME);
		Assert.assertEquals("Ожидается одна тренировка", Integer.valueOf(1), Integer.valueOf(leoProfile.getTrainingList().size()));
		
		leoProfile.addTraining(new TrainingNumbersImpl());
		Assert.assertEquals("Ожидается две тренировки", Integer.valueOf(2), Integer.valueOf(leoProfile.getTrainingList().size()));
	}
	
	
	@Test
	public void testSize() {
		Assert.assertEquals("Ожидается один профиль", Integer.valueOf(1), profileService.size());
	}
	
	@Test
	public void testProgress() {
		ProfileImpl leoProfile = profileService.findByName(Utils.LEO_PROFILE_NAME);
		
		for (AbstractTraining<?> trainting: leoProfile.getTrainingList()) {
			checkProgressIsEmpty(trainting);
			System.out.println(trainting.getName() + " progress: " + TrainingStatisticHelper.getProgressPercentage(trainting) + "%");
		}
		/*
		for (AbstractTraining<?> trainting: leoProfile.getTrainingList()) {
			if (trainting instanceof TrainingAmountsImpl) {
				trainting.process(Integer.valueOf(0), ActionType.View);
				trainting.process(Integer.valueOf(1), ActionType.View);
				trainting.process(Integer.valueOf(3), ActionType.View);
				trainting.process(Integer.valueOf(4), ActionType.View);
				trainting.process(Integer.valueOf(0), ActionType.View);
				Utils.TESTS.debug(TrainingStatisticHelper.printProgressStat(trainting));
			}
		}
		
		for (AbstractTraining trainting: leoProfile.getTrainingList()) {
			if (trainting instanceof TrainingAmountsImpl) {
				Assert.assertEquals(2, trainting.getProgressFor(0).size());
				Assert.assertEquals(1, trainting.getProgressFor(1).size());
				Assert.assertTrue(trainting.getProgressFor(2).isEmpty());
				Assert.assertEquals(1, trainting.getProgressFor(3).size());
				Assert.assertEquals(1, trainting.getProgressFor(4).size());
				Assert.assertTrue(trainting.getProgressFor(5).isEmpty());
			} else {
				checkProgressIsEmpty(trainting);
			}
			System.out.println(trainting.getName() + " progress: " + TrainingStatisticHelper.getProgressPercentage(trainting) + "%");
		}*/
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testProgressIllegalAgrumentNegative() {
		ProfileImpl leoProfile = profileService.findByName(Utils.LEO_PROFILE_NAME);		
		for (AbstractTraining<?> trainting: leoProfile.getTrainingList()) {
			trainting.process();
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testProgressIllegalAgrumentMoreThenMax() {
		ProfileImpl leoProfile = profileService.findByName(Utils.LEO_PROFILE_NAME);		
		for (AbstractTraining<?> trainting: leoProfile.getTrainingList()) {
			trainting.process();
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testProgressIllegalAgrumentNull() {
		ProfileImpl leoProfile = profileService.findByName(Utils.LEO_PROFILE_NAME);		
		for (AbstractTraining<?> trainting: leoProfile.getTrainingList()) {
			trainting.process();
		}
	}

	private void checkProgressIsEmpty(AbstractTraining<?> trainting) {
		/*
		Assert.assertTrue(trainting.getProgressFor(0).isEmpty());
		Assert.assertTrue(trainting.getProgressFor(1).isEmpty());
		Assert.assertTrue(trainting.getProgressFor(2).isEmpty());
		Assert.assertTrue(trainting.getProgressFor(3).isEmpty());
		Assert.assertTrue(trainting.getProgressFor(4).isEmpty());
		Assert.assertTrue(trainting.getProgressFor(5).isEmpty());
		*/
	}
}
