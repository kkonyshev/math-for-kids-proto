package mfk.api;

import java.util.List;

public interface ProfileService {
	Profile findByName(String profileName);
	List<Profile> listAll();
	Integer size();
	void save(Profile profile);
	void remove(Profile profile);
}