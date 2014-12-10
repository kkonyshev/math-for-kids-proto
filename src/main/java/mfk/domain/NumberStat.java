package mfk.domain;

import java.io.Serializable;

/**
 * 
 * @author kkonyshev
 *
 */
public class NumberStat implements Serializable {
	private static final long serialVersionUID = -1848121596397595423L;
	
	private Long profileId;
	private Integer number;
	private Integer count;
	
	public Long getProfileId() {
		return profileId;
	}
	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
}
