package kkonyshev.mfk;

/**
 * Тренинг
 * 
 * @author kkonyshev
 *
 */
public class AbstractTraining {
	private String name;
	public Object picture;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Тренировка{" + name + "}";
	}
}
