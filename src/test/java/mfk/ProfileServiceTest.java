package mfk;

import java.util.ArrayList;
import java.util.List;

import mfk.domain.NumberStat;
import mfk.domain.Profile;
import mfk.domain.ProfileService;

import org.junit.Assert;
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
public class ProfileServiceTest {
	
	@Autowired
	@Qualifier(value="mockProfileService")
	private ProfileService profileService;
	
	@Test
	public void testNumberStat() {
		Profile leoProfile = getProfile();
		
		List<Integer> learnedList = profileService.getLearnedNumber(leoProfile);
		Assert.assertTrue("Не должно быть изученных цифр", learnedList.isEmpty());
	}
	
	@Test
	public void testSetNumberStatByDto() {
		Profile leoProfile = getProfile();
		Long profileId = leoProfile.getId();
		
		NumberStat numberStat = new NumberStat();
		numberStat.setProfileId(profileId);
		numberStat.setNumber(1);
		numberStat.setCount(Profile.DEFAUL_VIEW_COUNT);
		profileService.setNumberStat(numberStat);
		
		List<Integer> learnedListOne = profileService.getLearnedNumber(leoProfile);
		Assert.assertEquals("Ожидается только одна изученная цифра", 1, learnedListOne.size());
		Integer firstItem = learnedListOne.get(0);
		Assert.assertEquals("Ожидается что цифра изученна (1)", Integer.valueOf(1), firstItem);
		
	}
	
	@Test
	public void testSetNumberStatbyDtoListAndGetLearnedNumber() {	
		Profile leoProfile = getProfile();
		Long profileId = leoProfile.getId();
		profileService.setNumberStat(leoProfile, 2, 2);
		profileService.setNumberStat(leoProfile, 2, 3);
		
		List<NumberStat> updateList = new ArrayList<NumberStat>();
		NumberStat e1 = new NumberStat();
		e1.setProfileId(profileId);
		e1.setNumber(99);
		e1.setCount(1);
		updateList.add(e1);
		NumberStat e2 = new NumberStat();
		e2.setProfileId(profileId);
		e2.setNumber(98);
		e2.setCount(Profile.DEFAUL_VIEW_COUNT);
		updateList.add(e2);
		profileService.setNumberStat(updateList);

		List<NumberStat> learnedListTwo = profileService.getNumberStat(leoProfile);
		Assert.assertEquals("Ожидается 3 цифры в статистике", 3, learnedListTwo.size());

		List<Integer> learnedListThree = profileService.getLearnedNumber(leoProfile);
		Assert.assertEquals("Ожидается что только одна цифра изученна", 1, learnedListThree.size());
	}
		
	@Test
	public void testSetNumberStatByNumberAndGetLearnedNumber() {
		Profile leoProfile = getProfile();

		profileService.setNumberStat(leoProfile, 2, Profile.DEFAUL_VIEW_COUNT+1);
		profileService.setNumberStat(leoProfile, 3, Profile.DEFAUL_VIEW_COUNT);
		List<Integer> learnedListFour = profileService.getLearnedNumber(leoProfile);
		Assert.assertEquals("Ожидается что 2 цифры изученны", 2, learnedListFour.size());
	}

	@Test
	public void testGetLearnedNumber() {
		Profile leoProfile = getProfile();

		profileService.increaseNumberStat(leoProfile, 97);
		List<Integer> learnedListFive = profileService.getLearnedNumber(leoProfile);
		Assert.assertEquals("Ожидается что 0 цифр изучено", 0, learnedListFive.size());
	}
	
	@Test
	public void testRemoveAndGetNextBatchToLearn() {
		Profile leoProfile = getProfile();
		
		profileService.remove(leoProfile);
		List<NumberStat> learnedListEmpty = profileService.getNumberStat(leoProfile);
		Assert.assertEquals("Статистика должна быть пустой", 0, learnedListEmpty.size());
		
		List<Integer> fileItemList = profileService.getNextBatchToLearn(leoProfile);
		Assert.assertEquals("Пачка должна иметь дефолтный размер", Profile.DEFAUL_BATCH_NUMBER.intValue(), fileItemList.size());
	}

	private Profile getProfile() {
		Profile leoProfile = (Profile) profileService.findProfileByName(BaseTest.LEO_PROFILE_NAME);
		return leoProfile;
	}
	
}
