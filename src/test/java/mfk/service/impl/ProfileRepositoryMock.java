package mfk.service.impl;

import java.util.Collections;
import java.util.List;

import mfk.ProfileImpl;
import mfk.service.ProfileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProfileRepositoryMock implements ProfileRepository {

	@Autowired
	private ProfileImpl leoProfile;
	
	@Override
	public ProfileImpl find(String profileName) {
		if (leoProfile!=null && profileName.equalsIgnoreCase(leoProfile.getName())) {
			return leoProfile;
		} else {
			throw new RuntimeException("profile not found by name: " + profileName);
		}
	}

	@Override
	public List<ProfileImpl> listAll() {
		return leoProfile!=null ? Collections.singletonList(leoProfile) : Collections.<ProfileImpl>emptyList();
	}

	@Override
	public Integer size() {
		return listAll().size();
	}

	@Override
	public void save(ProfileImpl profile) {
		find(profile.getName());
		leoProfile = profile;
	}

	@Override
	public void remove(ProfileImpl profile) {
		find(profile.getName());
		leoProfile = null;
	}
}
