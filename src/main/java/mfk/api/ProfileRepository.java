package mfk.api;

import java.util.List;

public interface ProfileRepository {
	Profile find(String profileName);
	List<Profile> listAll();
	Integer size();
	void save(Profile profile);
	void remove(Profile profile);
}
