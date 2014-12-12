package mfk.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public class ProfileDaoMock implements ProfileDao, Serializable {
	private static final long serialVersionUID = -426482358891148908L;

	private Set<Profile> profileSet = new HashSet<Profile>();

	@Override
	public Profile find(String profileName) {
		for (Profile profile: profileSet) {
			if (profileName.equalsIgnoreCase(profile.getName())) {
				return profile;
			}
		}
		throw new ProfileNotFoundException("profile not found by name: " + profileName);
	}

	@Override
	public List<Profile> listAll() {
		return new ArrayList<Profile>(profileSet);
	}

	@Override
	public Integer size() {
		return listAll().size();
	}

	@Override
	public void save(Profile profile) {
		profileSet.add(profile);
	}

	@Override
	public void remove(Profile profile) {
		profileSet.remove(profile);
	}

	public void setProfileSet(Set<Profile> profileSet) {
		this.profileSet = profileSet;
	}
}
