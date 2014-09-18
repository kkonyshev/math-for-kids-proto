package kkonyshev.mfk;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
	
	public void addTraining(AbstractTraining training) {
		trainingList.add(training);
	}
	
	public Integer getTotalYear() {
		return getCalendarFieldDiff(Calendar.YEAR);
	}
	
	public Integer getTotalMonth() {
		return getCalendarFieldDiff(Calendar.MONTH);
	}

	private Integer getCalendarFieldDiff(int field) {
		Calendar now   = Calendar.getInstance();
		Calendar birth = Calendar.getInstance();
		birth.setTime(birthDate);
		return now.get(field) - birth.get(field);
	}
	
	/*
	 * 
	 */
	private final static String DATE_FORMAT_PATTERN="yyyy-MM-dd";
	
	private String parceDate(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_PATTERN);
		return simpleDateFormat.format(date);
	}
	
	@Override
	public String toString() {
		return "[" + name + "][" + parceDate(birthDate) + "]" + trainingList;
	}
}
