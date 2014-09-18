package kkonyshev.mfk.service;

import java.util.Collections;
import java.util.List;

import kkonyshev.mfk.Profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class ProfileRepositoryMock implements ProfileRepository {

	@Autowired
	@Qualifier("leoProfile")
	private Profile leoProfile;
	
	@Override
	public Profile find(String profileName) {
		if (leoProfile!=null && profileName.equalsIgnoreCase(leoProfile.getName())) {
			return leoProfile;
		} else {
			throw new RuntimeException("profile not found by name: " + profileName);
		}
	}

	@Override
	public List<Profile> listAll() {
		return leoProfile!=null ? Collections.singletonList(leoProfile) : Collections.<Profile>emptyList();
	}

	@Override
	public Integer size() {
		return listAll().size();
	}

	@Override
	public void save(Profile profile) {
		find(profile.getName());
		leoProfile = profile;
	}

	@Override
	public void remove(Profile profile) {
		find(profile.getName());
		leoProfile = null;
	}
}
