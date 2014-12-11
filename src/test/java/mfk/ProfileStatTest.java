package mfk;

import java.util.List;

import mfk.domain.NumberStat;
import mfk.domain.Profile;
import mfk.domain.ProfileService;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context-test.xml"})
public class ProfileStatTest {
	
	@Autowired
	@Qualifier(value="mockProfileService")
	private ProfileService profileService;
	
	@Test
	public void testNumberStat() {
		Profile leoProfile = (Profile) profileService.findProfileByName(BaseTest.LEO_PROFILE_NAME);
		Long profileId = leoProfile.getId();
		
		List<Integer> learnedList = profileService.getLearnedNumber(leoProfile);
		Assert.assertTrue("Не должно быть изученных цифр", learnedList.isEmpty());
		
		NumberStat numberStat = new NumberStat();
		numberStat.setProfileId(profileId);
		numberStat.setNumber(1);
		numberStat.setCount(Profile.DEFAUL_VIEW_COUNT);
		profileService.setNumberStat(numberStat);
		
		List<Integer> learnedListOne = profileService.getLearnedNumber(leoProfile);
		Assert.assertTrue("Ожидается только одна изученная цифра", learnedListOne.size()==1);
		Integer firstItem = learnedListOne.get(0);
		Assert.assertEquals("Ожидается что цифра изученна (1)", Integer.valueOf(1), firstItem);
		
		profileService.setNumberStat(leoProfile, 2, 2);
				
		List<NumberStat> learnedListTwo = profileService.getNumberStat(leoProfile);
		Assert.assertTrue("Ожидается две цифры в статистике", learnedListTwo.size()==2);
		
		List<Integer> learnedListThree = profileService.getLearnedNumber(leoProfile);
		Assert.assertTrue("Ожидается что все ещё только одна цифра изученна", learnedListThree.size()==1);
	}
	
}
