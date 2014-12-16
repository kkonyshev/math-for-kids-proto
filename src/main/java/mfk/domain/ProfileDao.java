package mfk.domain;

import java.util.List;

/**
 * DAO профилей
 * 
 * @author kkonyshev
 *
 */
public interface ProfileDao {
	Profile find(String profileName);
	List<Profile> listAll();
	Integer size();
	void save(Profile profile);
	void remove(Profile profile);
}
