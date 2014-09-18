package kkonyshev.mfk.service;

import java.util.List;

import kkonyshev.mfk.Profile;

public interface ProfileService {
	Profile findByName(String profileName);
	List<Profile> listAll();
	Integer size();
	void save(Profile profile);
	void remove(Profile profile);
}