package mfk;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Профиль обучающегося
 * 
 * @author kkonyshev
 *
 */
public class ProfileImpl {
	/**
	 * Имя профиля
	 */
	private String name;
	
	/**
	 * Дата рождения обучающегося
	 */
	private Date birthDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
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
		birth.setTime(this.birthDate);
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
		return "[" + name + "][" + parceDate(this.birthDate) + "]";
	}
}
