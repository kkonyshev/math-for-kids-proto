package mfk.domain;

import java.util.List;

/**
 * 
 * @author kkonyshev
 *
 */
public interface ProfileService {
	
	IProfile findProfileByName(String profileName);
	List<IProfile> getProfileList();
	Integer profileCount();
	void save(IProfile profile);
	void remove(IProfile profile);

	void setNumberStat(IProfile profile, Integer number, Integer count);
	void setNumberStat(NumberStat numberStat);
	
	List<NumberStat> getNumberStat(IProfile profile);
	List<Integer> getLearnedNumber(IProfile profile);
}