package mfk.domain;

import java.util.List;

/**
 * DAO профилей
 * 
 * @author kkonyshev
 *
 */
public interface ProfileDao {
	IProfile find(String profileName);
	List<IProfile> listAll();
	Integer size();
	void save(IProfile profile);
	void remove(IProfile profile);
}
