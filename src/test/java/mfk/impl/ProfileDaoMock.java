package mfk.impl;

import java.util.Collections;
import java.util.List;

import mfk.domain.IProfile;
import mfk.domain.ProfileDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProfileDaoMock implements ProfileDao {

	@Autowired
	private IProfile leoProfile;
	
	@Override
	public IProfile find(String profileName) {
		if (leoProfile!=null && profileName.equalsIgnoreCase(leoProfile.getName())) {
			return leoProfile;
		} else {
			throw new RuntimeException("profile not found by name: " + profileName);
		}
	}

	@Override
	public List<IProfile> listAll() {
		return leoProfile!=null ? Collections.singletonList(leoProfile) : Collections.<IProfile>emptyList();
	}

	@Override
	public Integer size() {
		return listAll().size();
	}

	@Override
	public void save(IProfile profile) {
		find(profile.getName());
		leoProfile = profile;
	}

	@Override
	public void remove(IProfile profile) {
		find(profile.getName());
		leoProfile = null;
	}
}
