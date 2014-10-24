package mfk.service.impl;

import java.util.List;

import mfk.ProfileImpl;
import mfk.service.ProfileRepository;
import mfk.service.ProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@Override
	public ProfileImpl findByName(String profileName) {
		return profileRepository.find(profileName);
	}
	
	@Override
	public List<ProfileImpl> listAll() {
		return profileRepository.listAll();
	}
	
	@Override
	public Integer size() {
		return profileRepository.size();
	}
	
	@Override
	public void save(ProfileImpl profile) {
		profileRepository.save(profile);
	}
	
	@Override
	public void remove(ProfileImpl profile) {
		profileRepository.remove(profile);
	}

	/*
	 * 
	 */

	public void setProfileRepository(ProfileRepository profileRepository) {
		this.profileRepository = profileRepository;
	}
}
