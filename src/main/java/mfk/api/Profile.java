package mfk.api;

/**
 * Профиль обучающегося
 * 
 * @author kkonyshev
 *
 */
public interface Profile {
	public String getName();
	public void setName(String name);
	public Integer getTotalYear();
	public Integer getTotalMonth();
}
