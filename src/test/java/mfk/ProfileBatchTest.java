package mfk;

import java.util.List;

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
public class ProfileBatchTest {
	
	@Autowired
	@Qualifier(value="mockProfileService")
	private ProfileService profileService;

	@Test
	public void testGetBatch() {
		ProfileImpl leoProfile = profileService.findByName(Utils.LEO_PROFILE_NAME);
		for (AbstractTraining trainting: leoProfile.getTrainingList()) {
			List<Integer> nextBatch = trainting.getNextBatch();
			System.out.println(trainting.getName() + ". Набор чисел для следующего обучения: " + nextBatch);
			Assert.assertEquals("Ожидается набор для просмотра по умолчанию: " + AbstractTraining.SUGGESTTED_STEP, AbstractTraining.SUGGESTTED_STEP.intValue(), nextBatch.size());
		}
	}
	
	@Test
	public void testRun() throws InterruptedException {
		ProfileImpl leoProfile = profileService.findByName(Utils.LEO_PROFILE_NAME);
		for (AbstractTraining trainting: leoProfile.getTrainingList()) {
			for (int c=0;c<10;c++) {
				Thread t = new TrainingRunnerImpl(trainting);
				t.start();
				t.join();
				if (c==6) {
					trainting.setExcludeNumber(4, 10);
				}
			}
			System.out.println(trainting.printProgressStatGraph());
		}
	}
}
