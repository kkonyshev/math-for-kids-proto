package mfk.impl;

import java.util.List;

import mfk.api.Profile;
import mfk.api.ProfileRepository;
import mfk.api.ProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@Override
	public Profile findByName(String profileName) {
		return profileRepository.find(profileName);
	}
	
	@Override
	public List<Profile> listAll() {
		return profileRepository.listAll();
	}
	
	@Override
	public Integer size() {
		return profileRepository.size();
	}
	
	@Override
	public void save(Profile profile) {
		profileRepository.save(profile);
	}
	
	@Override
	public void remove(Profile profile) {
		profileRepository.remove(profile);
	}

	/*
	 * 
	 */

	public void setProfileRepository(ProfileRepository profileRepository) {
		this.profileRepository = profileRepository;
	}
}
