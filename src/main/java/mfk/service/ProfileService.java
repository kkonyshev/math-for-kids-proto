package mfk.service;

import java.util.List;

import mfk.ProfileImpl;

public interface ProfileService {
	ProfileImpl findByName(String profileName);
	List<ProfileImpl> listAll();
	Integer size();
	void save(ProfileImpl profile);
	void remove(ProfileImpl profile);
}