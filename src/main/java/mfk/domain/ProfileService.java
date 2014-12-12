package mfk.domain;

import java.util.Collection;
import java.util.List;

/**
 * 
 * @author kkonyshev
 *
 */
public interface ProfileService {
	
	Profile findProfileByName(String profileName);
	List<Profile> getProfileList();
	Integer profileCount();
	void save(Profile profile);
	void remove(Profile profile);

	void setNumberStat(Profile profile, Integer number, Integer count);
	void setNumberStat(NumberStat numberStat);
	void setNumberStat(Collection<NumberStat> numberStatCollection);
	void increaseNumberStat(Profile profile, Integer number);
	
	List<NumberStat> getNumberStat(Profile profile);
	List<Integer> getLearnedNumber(Profile profile);
	List<Integer> getNextBatchToLearn(Profile profile);
}