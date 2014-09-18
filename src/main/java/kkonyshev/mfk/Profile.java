package kkonyshev.mfk;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Профиль обучающегося
 * 
 * @author kkonyshev
 *
 */
public class Profile {
	/**
	 * Имя профиля
	 */
	private String name;
	
	/**
	 * Дата рождения обучающегося
	 */
	private Date birthDate;
	
	/**
	 * 
	 */
	private Set<AbstractTraining> trainingList = new HashSet<AbstractTraining>();

	public String getName() {
		return name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public Set<AbstractTraining> getTrainingList() {
		return trainingList;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public void setTrainingList(Set<AbstractTraining> trainingList) {
		this.trainingList = trainingList;
	}
	
	/*
	 * 
	 */
	
	@Override
	public String toString() {
		return "[" + name + "][" + birthDate + "]" + trainingList;
	}
}
