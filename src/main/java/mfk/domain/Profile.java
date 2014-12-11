package mfk.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Профиль обучающегося
 * 
 * @author kkonyshev
 *
 */
public class Profile implements Serializable {
	
	public static final Integer DEFAUL_MIN_NUMBER = 0;
	public static final Integer DEFAUL_MAX_NUMBER = 100;
	public static final Integer DEFAUL_VIEW_COUNT = 5;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8604934837020528661L;

	/*
	 * 
	 */
	private final static String DATE_FORMAT_PATTERN = "yyyy-MM-dd";
	
	/**
	 * Идентификатор
	 */
	private Long id;
	
	/**
	 * Имя профиля
	 */
	private String name;
	
	/**
	 * Дата рождения обучающегося
	 */
	private Date birthDate;

	/**
	 * Статистика просмотра цифр
	 */
	private List<NumberStat> numberCount;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<NumberStat> getNumberCount() {
		return numberCount;
	}

	public void setNumberCount(List<NumberStat> numberCount) {
		this.numberCount = numberCount;
	}

	public Date getBirthDate() {
		return this.birthDate;
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
	
	private String parceDate(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_PATTERN);
		return simpleDateFormat.format(date);
	}
	
	@Override
	public String toString() {
		return "[" + name + "][" + parceDate(this.birthDate) + "]";
	}
}
